package product1.product.dto

data class ProductDescriptionPayload(
    /**
     * 상품 이미지
     */
    val images: List<String>,

    /**
     * 상품 컨텐츠
     */
    val contents: List<String>
)
