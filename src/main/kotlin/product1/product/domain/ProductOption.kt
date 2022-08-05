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
     * 상품 옵션명
     */
    val name: String,

    /**
     * 상품 옵션값 리스트
     */
    val values: List<ProductOptionValue>
) {
    companion object {
        /**
         * 상품 옵션 생성
         * @param type 옵션 구분
         * @param name 옵션명
         * @param values 옵션값 리스트
         */
        private fun of(type: ProductOptionType, name: String, values: List<ProductOptionValue>): ProductOption =
            ProductOption(
                type = type,
                name = name,
                values = values
            )

        /**
         * 필수 옵션 생성
         * @param name 옵션명
         * @param values 옵션값 리스트
         */
        fun ofPrimary(name: String, values: List<ProductOptionValue>): ProductOption =
            of(ProductOptionType.PRIMARY, name, values)

        /**
         * 추가 옵션 생성
         * @param name 옵션명
         * @param values 옵션값 리스트
         */
        fun ofExtra(name: String, values: List<ProductOptionValue>): ProductOption =
            of(ProductOptionType.EXTRA, name, values)

    }
}