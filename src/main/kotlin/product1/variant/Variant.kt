package product1.variant

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.Instant

/**
 * 상품 정보
 */
@Document
data class Variant(
    /**
     * 고유 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 상품 번호
     */
    val productId: ObjectId,

    /**
     * 상품명 (xxx- x/y/z)
     */
    val name: String,

    /**
     * 재고 상품 옵션
     */
    val options: Set<VariantOption>,

    /**
     * 상품 옵션 고유 코드
     */
    val code: String,

    /**
     *  재고 상품 그룹 단위
     */
    val sku: String,

    /**
     * 재고 상품 가격
     */
    val price: Int,

    /**
     * 재고
     */
    val stock: Int,

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
