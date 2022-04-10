package product1.delivery

/**
 * 상세 배송비 설정
 */
data class DeliveryPriceDetail (

    /**
     * 고유 번호
     */
    val id :String,

    /**
     * 배송비
     */
    val amount: Int,

    /**
     * 배송비 상품 조건
     */
    val term: DeliveryProductTerm?
)