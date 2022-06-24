package product1.product.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import product1.product.dto.ProductDescriptionPayload
import java.time.Instant

/**
 * 상품 상세 설명
 */
data class ProductDescription(

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
        fun create(payload: ProductDescriptionPayload) = ProductDescription(
            images = payload.images,
            contents = payload.contents
        )
    }
}