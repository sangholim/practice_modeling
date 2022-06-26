package product1.product.dto

import product1.product.domain.ProductDescription
import product1.product.domain.ProductOption

/**
 * 상품 요청 필드
 */
data class ProductPayload(
    /**
     * 상품명
     */
    val name: String,
    /**
     * 기본 가격
     */
    val price: Int,
    /**
     * 상품 설명
     */
    val description: ProductDescription,

    /**
     * 상품 옵션
     */
    val options: List<ProductOption>,

    /**
     * 상품 추가
     */
    val extras: List<ProductOption>?
)