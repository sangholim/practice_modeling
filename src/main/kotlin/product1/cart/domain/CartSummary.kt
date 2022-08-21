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
        fun of(subtotal: Int = 0): CartSummary = CartSummary(
            subtotal = subtotal,
            shippingAmount = 5000,
            discountAmount = 0
        )

        /**
         * 장바구니 금액 요약 데이터 생성
         * @param lineItems 구매 항목
         */
        fun of(lineItems: List<LineItem>): CartSummary = CartSummary(
            subtotal = lineItems.sumOf { it.sumOfTotal() },
            shippingAmount = 5000,
            discountAmount = 0
        )
    }
}
