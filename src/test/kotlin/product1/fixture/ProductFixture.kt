package product1.fixture

import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.product.domain.ProductOptionType
import product1.product.dto.ProductPayload
import kotlin.random.Random

object ProductFixture {

    fun createPayload() = ProductPayload(
        name = RandomStringUtils.randomAlphabetic(10),
        price = Random.nextInt(1, 10) * 100,
        description = ProductDescriptionFixture.createPayload(),
        options = listOf(
            ProductOptionFixture.createPayloadByType(ProductOptionType.COMBINATION),
            ProductOptionFixture.createPayloadByType(ProductOptionType.COMBINATION),
            ProductOptionFixture.createPayloadByType(ProductOptionType.COMBINATION),
            ProductOptionFixture.createPayloadByType(ProductOptionType.EXTRA)
        )
    )
}