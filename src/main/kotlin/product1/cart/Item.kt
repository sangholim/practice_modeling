package product1.cart

import org.bson.types.ObjectId

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
)
