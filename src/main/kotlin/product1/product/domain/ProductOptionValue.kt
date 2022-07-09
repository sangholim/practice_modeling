package product1.product.domain

import product1.product.ProductOptionCode

/**
 * 상품 옵션 값
 */
data class ProductOptionValue(

    /**
     * 상품 옵션값
     */
    val value: String,

    /**
     * 매핑 코드
     */
    val code: String,

    /**
     * 상풉 옵션 가격
     */
    val price: Int,
) {
    companion object {
        fun of(value: String, price: Int): ProductOptionValue = ProductOptionValue(
            value = value,
            code = ProductOptionCode.generateCode(),
            price = price
        )
    }
}
