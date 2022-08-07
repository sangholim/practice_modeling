package product1.cart.domain

import org.bson.types.ObjectId

/**
 * 구매 항목
 */
data class LineItem(
    /**
     * 상품 고유 번호
     */
    val productId: ObjectId,

    /**
     * 상품명
     */
    val name: String,

    /**
     * 상품 메인 이미지
     */
    val image: String,

    /**
     * 옵션 상품 항목
     */
    val items: List<Item>

)
