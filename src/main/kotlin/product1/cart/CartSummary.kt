package product1.cart

/**
 * 장바구니 금액 요약
 */
data class CartSummary(
    /**
     * 옵션 상품 총 금액
     */
    val subtotal: Int,

    /**
     * 배송비
     */
    val shippingAmount: Int,

    /**
     * 할인 금액
     */
    val discountAmount: Int,

    /**
     * 결제 총 금액
     */
    val total: Int
)
