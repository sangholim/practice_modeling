package product1.product.dto

data class VariantPayload(

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
