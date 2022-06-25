package product1.product.domain

/**
 * 상품 옵션
 */
data class ProductOption(

    /**
     * 옵션 타입
     */
    val type: ProductOptionType,

    /**
     * 옵션명
     * 추가 옵션인 경우: 추가옵션 텍스트 고정
     */
    val name: String,

    /**
     * 옵션값
     */
    val value: String,

    /**
     * 옵션 코드
     */
    val code: String,

    /**
     * 상품 옵션 가격
     */
    val price: Int
)