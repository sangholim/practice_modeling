package product1.product.domain


/**
 * 상품 상세 설명
 */
data class ProductDescription(

    /**
     * 상품 이미지들
     */
    val images: List<String>,

    /**
     * 상품 설명 내용 [고객 지원 포함]
     */
    val contents: List<String>,
)