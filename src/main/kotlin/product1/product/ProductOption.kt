package product1.product

/**
 * 상품 상세
 */
data class ProductOption(

    /**
     * 상품 상세 Id
     */
    val id: String,

    /**
     * 상품 Id
     */
    val productId: String,

    /**
     * 옵션 타입
     */
    val type: ProductOptionType,

    /**
     * 상품 옵션 코드
     */
    val code: String,

    /**
     * 상품 옵션 변경값 리스트
     */
    val variants: List<Variant>,

    /**
     * 상품 옵션 추가 가격
     */
    val price: Int,

    /**
     * 상품 마진
     */
    val margin: Int
)