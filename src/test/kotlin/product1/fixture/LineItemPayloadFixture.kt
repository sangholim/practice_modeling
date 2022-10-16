package product1.fixture

import org.bson.types.ObjectId
import product1.cart.dto.ItemPayload
import product1.cart.dto.LineItemPayload

fun lineItemPayload(blocks: LineItemPayloadBuilder.() -> Unit = {}) =
    LineItemPayloadBuilder().apply(blocks).build()

class LineItemPayloadBuilder {

    var productId: ObjectId = ObjectId.get()
    var items: List<ItemPayload> = listOf()

    fun build(): LineItemPayload =
        LineItemPayload(productId, items)
}