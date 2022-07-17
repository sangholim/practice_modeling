package product1.variant

import product1.product.domain.ProductOptionValue

/**
 * 재고 상품 옵션
 */
data class VariantOption(

    /**
     * 옵션 이름
     */
    val name: String,

    /**
     * 옵션 값
     */
    val value: String,

    /**
     * 옵션 가격
     */
    val price: Int,

    /**
     * 옵션 코드
     */
    val code: String
) {
    companion object {
        /**
         * 상품 구성 옵션 객체 생성
         * @param name 옵션명
         * @param productOptionValue 상품 옵션값
         * @return VariantOption
         */
        fun of(name: String, productOptionValue: ProductOptionValue): VariantOption =
            VariantOption(name, productOptionValue.value, productOptionValue.price, productOptionValue.code)
    }
}
