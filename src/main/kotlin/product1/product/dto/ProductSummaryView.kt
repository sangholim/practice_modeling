package product1.product.dto

import org.bson.types.ObjectId
import product1.product.domain.Product
import product1.product.domain.ProductStatus

/**
 * 상품 요약 데이터
 */
data class ProductSummaryView(

    /**
     * 고유 번호
     */
    val id: ObjectId,

    /**
     * 상품명
     */
    val name: String,

    /**
     * 상품 상태
     */
    val status: ProductStatus,

    /**
     * 기본 판매 가격
     */
    val price: Int,

    /**
     * 상품 이미지
     */
    val image: String
)

fun Product.toProductSummaryView() = ProductSummaryView(
    id = id!!,
    name = name,
    status = status,
    price = price,
    image = description.images[0]
)
