package product1.fixture

import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.product.dto.ProductDescriptionPayload

object ProductDescriptionFixture {

    fun createPayload() = ProductDescriptionPayload(
        images = listOf(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)),
        contents = listOf(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)),
    )
}