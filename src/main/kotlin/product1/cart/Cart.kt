package product1.cart

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.Instant

/**
 * 장바구니
 */
@Document
data class Cart(

    /**
     * 장바구니 고유 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 구매 항목
     */
    val lineItems: List<LineItem>,

    /**
     * 구매 금액 요약
     */
    val summary: CartSummary,

    /**
     * 생성일
     */
    @CreatedDate
    val createAt: Instant? = null,

    /**
     * 수정일
     */
    @LastModifiedDate
    val modifiedAt: Instant? = null

)