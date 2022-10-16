package product1.fixture

import org.bson.types.ObjectId
import product1.cart.dto.ItemPayload

fun itemPayload(blocks: ItemPayloadBuilder.() -> Unit = {}) =
    ItemPayloadBuilder().apply(blocks).build()

class ItemPayloadBuilder {
    var variantId: ObjectId = ObjectId.get()
    var count: Int = 0

    fun build(): ItemPayload =
        ItemPayload(variantId, count)
}