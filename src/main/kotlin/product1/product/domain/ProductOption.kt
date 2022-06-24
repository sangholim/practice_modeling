package product1.product.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.data.mongodb.core.mapping.MongoId
import product1.product.dto.ProductOptionPayload
import java.time.Instant
import java.util.*

/**
 * 상품 옵션
 */
@Document
data class ProductOption(

    /**
     * 상품 옵션 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 상품 번호
     */
    val productId: ObjectId,

    /**
     * 옵션 타입
     */
    val type: ProductOptionType,

    /**
     * 상품 옵션 코드
     */
    val code: String,

    /**
     * 상품 옵션 변경값 리스트
     */
    @ReadOnlyProperty
    @DocumentReference
    val variants: List<Variant>? = null,

    /**
     * 상품 옵션 가격
     */
    val price: Int,

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
    companion object {
        fun create(productId: ObjectId, payload: ProductOptionPayload) = ProductOption(
            productId = productId,
            type = payload.type,
            code = UUID.randomUUID().toString(),
            price = payload.price
        )
    }
}