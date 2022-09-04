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
import product1.variant.VariantService

@Service
class CartFacadeService(
    private val cartService: CartService,
    private val productService: ProductService,
    private val variantService: VariantService
) {

    /**
     * 장바구니 상품 담기
     * 장바구니 조회
     * 요청한 구매 항목의 상품 조회
     * 요청한 구매 항목의 상품 옵션 조회
     *
     * 요청한 구매 항목이 업는 경우 - 장바구니에 구매항목을 추가한다
     * 요청한 구매 항목이 있는 경우 - 장바구니에 구매항목을 업데이트한다
     *
     * 장바구니 구매항목이 변경된 경우 - 정산 요약을 업데이트 한다
     *
     * @param employeeId 고객 번호
     * @param payload 장바구니 구매 항목 필드
     */
    suspend fun addLineItem(employeeId: ObjectId, payload: LineItemPayload): Cart {
        val cart = cartService.getCart(employeeId) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        val product = productService.getProduct(payload.productId)
        val variants = payload.items.map {
            variantService.getVariantByIdAndProductId(it.variantId, payload.productId) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND
            )
        }

        val items = payload.items.map { item ->
            val variant = variants.first { item.variantId == it.id }
            Item.of(variant, item.count)
        }

        val lineItem = cart.findLineItem(payload.productId)?.addItems(items) ?: LineItem.of(product, items)
        return cartService.updateCart(cart.addLineItem(lineItem).updateSummary())
    }
}