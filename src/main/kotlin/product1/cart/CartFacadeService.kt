package product1.cart

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import product1.cart.domain.Cart
import product1.cart.domain.Item
import product1.cart.domain.LineItem
import product1.cart.dto.LineItemPayload
import product1.product.ProductService
import product1.variant.Variant
import product1.variant.VariantService

@Service
class CartFacadeService(
    private val cartService: CartService,
    private val productService: ProductService,
    private val variantService: VariantService
) {

    /**
     * 장바구니 상품 담기
     * 항목이 없는 경우, 항목 추가
     * 항목이 있는 경우, 수량만 추가
     *
     * @param employeeId 고객 번호
     * @param payload 장바구니 구매 항목 필드
     */
    suspend fun addLineItem(employeeId: ObjectId, payload: LineItemPayload): Cart {
        val cart = cartService.getCart(employeeId) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        val product = productService.getProduct(payload.productId)
        // 장바구니에 담는 옵션 상품들
        val items = payload.items.map { item ->
            val variant = getAvailableVariant(item.variantId, product.id!!, item.count)
            Item.of(product, variant, item.count)
        }
        /**
         * 구매 항목 없는 경우 - 구매 항목 추가
         *
         * 구매 항목 있는 경우 - 옵션 상품 담기
         *  옵션 상품이 있는 경우 - 수량을 늘린다
         *  옵션 상품이 없는 경우 - 옵션 상품을 추가한다
         */
        val lineItem = cart.findLineItem(payload.productId)?.addItems(items) ?:
            return cartService.updateCart(cart.addLineItem(LineItem.of(product, items)))
        return cartService.updateCart(cart.updateLineItem(lineItem))
    }

    /**
     * 이용 가능한 상품 데이터 얻기
     * @param variantId 상품 번호
     * @param productId 상품 기본 정보 번호
     * @param count 상품 수량
     */
    private suspend fun getAvailableVariant(variantId: ObjectId, productId: ObjectId, count: Int): Variant {
        val variant = variantService.getVariantByIdAndProductId(variantId, productId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (!variant.isExist(count)) throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        return variant
    }
}