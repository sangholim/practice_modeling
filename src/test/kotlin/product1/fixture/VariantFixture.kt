package product1.fixture

import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.product.dto.VariantPayload

object VariantFixture {

    fun createPayload() = VariantPayload(
        sku = RandomStringUtils.randomAlphabetic(10),
        name = RandomStringUtils.randomAlphabetic(10),
        value = RandomStringUtils.randomAlphabetic(10)
    )
}