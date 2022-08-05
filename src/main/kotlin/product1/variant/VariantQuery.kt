package product1.variant

/**
 * 옵션 상품 질의 클래스
 */
data class VariantQuery (
    /**
     * 옵션 코드
     */
    val code: String,

    /**
     * 옵션 상품 수량
     */
    val count: Int
)