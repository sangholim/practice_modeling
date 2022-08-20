package product1.variant

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import product1.product.domain.Product
import java.time.Instant
import java.util.*

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
     * 상품명 (옵션1/옵션2/옵션3)
     */
    val name: String,

    /**
     * 재고 상품 옵션
     */
    val options: List<VariantOption>,

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
) {

    /**
     * 옵션 상품 존재 여부
     * @param count 수량
     */
    fun isExist(count: Int): Boolean = stock >= count

    companion object {
        /**
         * 상품 구성 객체 생성
         * @param product 상품 정보
         * @param options 상품 구성 옵션 리스트
         * @param stock 재고
         */
        fun of(product: Product, options: List<VariantOption>, stock: Int): Variant {
            val name = options.joinToString("/") { it.value }
            val code = options.joinToString("-") { it.code }
            val price = product.price + options.sumOf { it.price }
            return Variant(
                productId = product.id!!,
                name = name,
                options = options,
                code = code,
                sku = UUID.randomUUID().toString(),
                price = price,
                stock = stock
            )
        }
    }
}
