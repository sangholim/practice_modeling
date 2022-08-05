package product1.variant

import org.bson.types.ObjectId

/**
 * 옵션 상품 응답값
 */
data class VariantView(
    /**
     * 옵션 상품 번호
     */
    val id: ObjectId,

    /**
     * 옵션 상품명
     */
    val name: String,

    /**
     * 옵션 상품 가격
     */
    val price: Int,

    /**
     * 옵션 상품 개수
     */
    val count: Int
)

fun Variant.toVariantView(count: Int) = VariantView(
    id = this.id!!,
    name = this.name,
    price = this.price,
    count = count
)