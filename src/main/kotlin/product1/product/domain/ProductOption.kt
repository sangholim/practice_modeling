package product1.product.domain

import product1.product.dto.ProductOptionPayload
import java.util.*

/**
 * 상품 옵션
 */
data class ProductOption(

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
    val variants: List<Variant>? = null,

    /**
     * 상품 옵션 가격
     */
    val price: Int
) {
    companion object {
        fun create(payload: ProductOptionPayload) = ProductOption(
            type = payload.type,
            code = UUID.randomUUID().toString(),
            price = payload.price,
            variants = payload.variants?.map (Variant::create)
        )
    }
}