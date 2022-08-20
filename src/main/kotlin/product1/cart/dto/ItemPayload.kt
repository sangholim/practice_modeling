package product1.cart.dto

import org.bson.types.ObjectId

/**
 * 장바구니 구매 옵션 상품 요청 필드
 */
data class ItemPayload (

    /**
     * 옵션 상품 번호
     */
    val variantId: ObjectId,

    /**
     * 구매 개수
     */
    val count: Int,

)