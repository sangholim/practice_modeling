package product1.fixture

import org.bson.types.ObjectId
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.product.domain.Product
import product1.product.dto.ProductPayload
import kotlin.random.Random

object ProductFixture {

    val ID = ObjectId.get()

    fun createProducts(): List<Product> {
        val list = mutableListOf<Product>()
        for(i in 1..16) {
            val payload = createPayload()
            val product = Product.create(CompanyFixture.id, payload).copy(id = ObjectId.get())
            list.add(product)
        }
        return list
    }

    fun createPayload() = ProductPayload(
        name = RandomStringUtils.randomAlphabetic(10),
        price = Random.nextInt(100, 110) * 100,
        description = ProductDescriptionFixture.create(),
        options = ProductOptionFixture.createOptions()
    )
}