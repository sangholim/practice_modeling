package product1.delivery

/**
 * 배송비 상품 조건
 */
data class DeliveryProductTerm(

    /**
     * 조건: kg, 원, 개수
     */
    val type: String,

    /**
     * 상품 조건 이상
     */
    val moreThan: Int,

    /**
     * 상품 조건 미만
     */
    val lessThan: Int
)