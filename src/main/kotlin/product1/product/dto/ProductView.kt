package product1.product.dto

import org.bson.types.ObjectId
import product1.product.domain.Product
import product1.product.domain.ProductDescription
import product1.product.domain.ProductOption
import product1.product.domain.ProductStatus

/**
 * 상품 응답 데이터
 */
data class ProductView(


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
     * 상품 설명
     */
    val description: ProductDescription,

    /**
     * 상품 옵션
     * 옵션명 기준 분류된 상품 옵션들
     */
    val options: Map<String, List<ProductOption>>,

    val extras: List<ProductOption>?
)

fun Product.toProductView() = ProductView(
    id = id!!,
    name = name,
    status = status,
    price = price,
    description = description,
    options = options.groupBy { it.name },
    extras = extras
)
