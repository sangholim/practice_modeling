package product1.cart.dto

import org.bson.types.ObjectId

/**
 * 장바구니 구매 상품 요청 필드
 */
data class LineItemPayload(
    /**
     * 상품 번호
     */
    val productId: ObjectId,

    /**
     * 옵션 상품 리스트
     */
    val items: List<ItemPayload>
)