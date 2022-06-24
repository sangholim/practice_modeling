package product1.product.dto

import product1.product.domain.ProductOptionType

data class ProductOptionPayload (

    /**
     * 옵션 타입
     */
    val type: ProductOptionType,

    /**
     * 상품 옵션 변경값 리스트
     */
    val variants: List<VariantPayload>? = null,

    /**
     * 상품 옵션 가격
     */
    val price: Int
)
