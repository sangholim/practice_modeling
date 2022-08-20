package product1.cart.domain

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
    val total: Int = subtotal + shippingAmount - discountAmount
) {
    companion object {

        /**
         * 장바구니 금액 요약 데이터 생성
         */
        fun of(): CartSummary = CartSummary(
            subtotal = 0,
            shippingAmount = 5000,
            discountAmount = 0
        )
    }
}
