package product1.product.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import product1.product.dto.ProductDescriptionPayload
import java.time.Instant

/**
 * 상품 상세 설명
 */
@Document
data class ProductDescription(

    /**
     * 상품 설명 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 상품 번호
     */
    val productId: ObjectId,

    /**
     * 상품 이미지들
     */
    val images: List<String>,

    /**
     * 상품 설명 내용 [고객 지원 포함]
     */
    val contents: List<String>,

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
        fun create(productId: ObjectId, payload: ProductDescriptionPayload) = ProductDescription(
            productId = productId,
            images = payload.images,
            contents = payload.contents
        )
    }
}