package product1.variant

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * 재고 관리 상품 정보
 */
@Document
data class Variant(
    /**
     * 고유 번호
     */
    val id: ObjectId? = null,

    /**
     * 재고 상품 옵션
     */
    val options: Set<VariantOption>,

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
