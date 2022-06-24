package product1.fixture

import org.bson.types.ObjectId
import product1.product.domain.ProductOptionType
import product1.product.dto.ProductOptionPayload
import kotlin.random.Random

object ProductOptionFixture {

    val ID = ObjectId.get()

    fun createPayloadByType(type: ProductOptionType): ProductOptionPayload = when (type) {
        ProductOptionType.EXTRA -> ProductOptionPayload(type = type, price = Random.nextInt(1, 10) * 100)
        ProductOptionType.COMBINATION -> ProductOptionPayload(
            type = type,
            variants = listOf(VariantFixture.createPayload(), VariantFixture.createPayload()),
            price = Random.nextInt(1, 10) * 100
        )
    }

}