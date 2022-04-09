package product1.product

/**
 * 선택 가능한 상품 옵션
 * 물류 서비스와 integration 을 통해 재고 체크 수행
 */
data class Variant(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 상품 옵션 번호
     */
    val productOptionId: String,

    /**
     * 재고 고유 번호
     */
    val sku: String,

    /**
     * 옵션명
     */
    val name: String,

    /**
     * 옵션값
     */
    val value: String
)