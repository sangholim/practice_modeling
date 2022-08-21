package product1.cart.domain

import org.bson.types.ObjectId
import product1.product.domain.Product

/**
 * 구매 항목
 */
data class LineItem(
    /**
     * 상품 고유 번호
     */
    val productId: ObjectId,

    /**
     * 상품명
     */
    val name: String,

    /**
     * 상품 메인 이미지
     */
    val image: String,

    /**
     * 옵션 상품 항목
     */
    val items: List<Item>

) {

    fun sumOfTotal() = items.sumOf { it.total }

    /**
     * 옵션 상품 담기
     * @param items 옵션 상품들
     */
    fun addItems(items: List<Item>): LineItem {
        val variantIds = this.items.map { it.variantId }
        val addItems = items.filter { !variantIds.contains(it.variantId) }
        val updateItems = this.items.map { item ->
            val updateItem = items.firstOrNull { item.variantId == it.variantId } ?: return@map item
            item.addCount(updateItem.count)
        }
        return copy(items = updateItems.plus(addItems))
    }


    companion object {
        /**
         * 상품 구매 항목 생성
         * @param product 상품
         * @param items 옵션 상품 리스트
         */
        fun of(product: Product, items: List<Item>): LineItem = LineItem(
            productId = product.id!!,
            name = product.name,
            image = product.description.images.first(),
            items = items
        )
    }
}
