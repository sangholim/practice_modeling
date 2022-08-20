package product1.cart.domain

import org.bson.types.ObjectId
import product1.variant.Variant

/**
 * 옵션 상품 항목
 */
data class Item(

    /**
     * 옵션 상품 번호
     */
    val variantId: ObjectId,

    /**
     * 옵션 상품명
     */
    val name: String,

    /**
     * 구매 개수
     */
    val count: Int,

    /**
     * 총 금액
     */
    val total: Int
) {
    companion object {
        /**
         *  옵션 상품 항목 데이터 생성
         * @param variant 옵션 상품
         * @param name 항목명
         * @param count 수량
         */
        fun of(variant: Variant, name: String, count: Int): Item = Item(
            variantId = variant.id!!,
            name = name,
            count = count,
            total = variant.price * count
        )
    }
}
