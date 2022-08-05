package product1.product.fixture

import org.bson.types.ObjectId
import product1.TestDataLoader
import product1.product.domain.Product
import product1.product.dto.ProductPayload
import kotlin.random.Random

object ProductFixture {

    private val COMPANY_ID = ObjectId.get()

    fun createProduct(): Product = Product.of(
        COMPANY_ID,
        "test",
        Random.nextInt(100, 110) * 200,
        ProductDescriptionFixture.create(),
        ProductOptionFixture.getProductOptions(),
        null
    ).copy(id = ObjectId.get())

    fun createPayload() = ProductPayload(
        name = TestDataLoader.generateAlphabet(10),
        price = Random.nextInt(100, 110) * 100,
        description = ProductDescriptionFixture.create(),
        options = ProductOptionFixture.getProductOptions(),
        extras = null
    )
}