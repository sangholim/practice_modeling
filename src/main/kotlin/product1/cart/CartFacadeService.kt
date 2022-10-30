package product1.cart

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import product1.cart.domain.Cart
import product1.cart.domain.Item
import product1.cart.domain.LineItem
import product1.cart.dto.ItemPayload
import product1.cart.dto.LineItemPayload
import product1.product.ProductService
import product1.product.domain.Product
import product1.variant.VariantService

@Service
class CartFacadeService(
    private val cartService: CartService,
    private val productService: ProductService,
    private val variantService: VariantService
) {

    /**
     * 장바구니 상품 담기
     *
     * 장바구니 조회
     * 요청한 구매 항목의 상품 조회
     *
     * 장바구니에 구매항목을 업데이트한다
     *
     * @param employeeId 고객 번호
     * @param payload 장바구니 구매 항목 필드
     */
    suspend fun addLineItem(employeeId: ObjectId, payload: LineItemPayload): Cart =
        cartService.getCart(employeeId)?.let { cart ->
            val product = productService.getProduct(payload.productId)
            val lineItem = payload.items.createLineItem(cart, product)
            cartService.updateCart(cart.addLineItem(lineItem).updateSummary())
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)


    /**
     * 구매 항목 제거
     *
     * 장바구니 조회
     * 구매 항목 제거
     * 결제 내역 갱신
     *
     * @param employeeId 고객 번호
     * @param productId 상품 번호
     */
    suspend fun deleteLineItem(employeeId: ObjectId, productId: ObjectId): Cart =
        cartService.getCart(employeeId)
            ?.deleteLineItem(productId)
            ?.updateSummary()
            ?.apply {
                cartService.updateCart(this)
            }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    /**
     * 구매 항목 생성
     *
     * 구매 항목이 업는 경우 - 구매항목을 추가한다
     * 구매 항목이 있는 경우 - 구매항목을 업데이트한다
     *
     * @param cart 장바구니
     * @param product 상품
     */
    private suspend fun List<ItemPayload>.createLineItem(cart: Cart, product: Product): LineItem =
        this.map { item ->
            createItem(item)
        }.let { items ->
            cart.findLineItem(product.id!!)?.addItems(items) ?: LineItem.of(product, items)
        }

    private suspend fun createItem(payload: ItemPayload): Item =
        variantService.getById(payload.variantId)
            ?.let { variant ->
                Item.of(variant, payload.count)
            } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

}