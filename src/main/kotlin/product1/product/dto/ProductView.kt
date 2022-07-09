package product1.product.dto

import org.bson.types.ObjectId
import product1.product.domain.*

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
     * 조합형 상품 옵션
     */
    val options: List<ProductOption>,

    /**
     * 추가 상품 옵션 리스트
     */
    val extras: List<ProductOption>?
)

fun Product.toProductView() = ProductView(
    id = id!!,
    name = name,
    status = status,
    price = price,
    description = description,
    options = options,
    extras = extras
)
