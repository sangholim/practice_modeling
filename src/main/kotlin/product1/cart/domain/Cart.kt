package product1.cart.domain

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
     * 직원 번호
     */
    val employeeId: ObjectId,

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

) {

    /**
     * 구매 항목 조회
     * @param productId 구매 항목
     */
    fun findLineItem(productId: ObjectId): LineItem? = lineItems.firstOrNull { it.productId == productId }

    /**
     * 구매 항목 추가
     * @param lineItem 구매 항목
     */
    fun addLineItem(lineItem: LineItem): Cart {
        val newLineItems = this.lineItems.plus(lineItem)
        val summary = CartSummary.of(newLineItems)
        return copy(lineItems = newLineItems, summary = summary)
    }

    /**
     * 구매 항목 업데이트
     * @param lineItem 구매 항목
     */
    fun updateLineItem(lineItem: LineItem): Cart {
        val updateLineItems = this.lineItems.map {
            if(it.productId != lineItem.productId) return@map it
            lineItem
        }
        val summary = CartSummary.of(updateLineItems)
        return copy(summary = summary, lineItems = updateLineItems)
    }

    companion object {

        /**
         * 빈 장바구니 생성
         * @param employeeId 회원 번호
         */
        fun of(employeeId: ObjectId): Cart = Cart(
            employeeId = employeeId,
            lineItems = listOf(),
            summary = CartSummary.of()
        )
    }
}