package product1.product.domain

import product1.product.dto.VariantPayload

/**
 * 선택 가능한 상품 옵션
 * 물류 서비스와 integration 을 통해 재고 체크 수행
 */
data class Variant(

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
) {
    companion object {
        fun create(payload: VariantPayload) = Variant(
            sku = payload.sku,
            name = payload.name,
            value = payload.value
        )
    }
}