package product1.product.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.Instant
import java.util.*

/**
 * 상품
 * 배송비는 배송비 정책에 준수한다.
 */
@Document
data class Product(

    /**
     * 고유 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 판매자(기업) 번호
     */
    val companyId: ObjectId,

    /**
     * 상품명
     */
    val name: String,

    /**
     * 상품 상태
     */
    val status: ProductStatus,

    /**
     * 상품 코드
     */
    val code: String,

    /**
     * 기본 판매 가격
     */
    val price: Int,

    /**
     * 상품 설명
     */
    val description: ProductDescription,

    /**
     * 상품 옵션
     */
    val options: List<ProductOption>,

    /**
     * 추가 상품
     */
    val extras: List<ProductOption>?,

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
        /**
         * 상품 생성
         * @param companyId 기업 번호
         * @param name 상품명
         * @param price 상품 기본 가격
         * @param description 상품설명
         * @param options 옵션리스트
         * @param extras 추가상품리스트
         * @return Product2
         */
        fun of(
            companyId: ObjectId,
            name: String,
            price: Int,
            description: ProductDescription,
            options: List<ProductOption>,
            extras: List<ProductOption>?
        ): Product =
            Product(
                companyId = companyId,
                name = name,
                status = ProductStatus.READY,
                code = UUID.randomUUID().toString(),
                price = price,
                description = description,
                options = options,
                extras = extras
            )
    }
}