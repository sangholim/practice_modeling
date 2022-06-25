package product1.fixture

import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.product.domain.ProductDescription

object ProductDescriptionFixture {

    fun create() = ProductDescription(
        images = listOf(
            "main-${RandomStringUtils.randomAlphabetic(5)}",
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5)
        ),
        contents = listOf(
            "main-${RandomStringUtils.randomAlphabetic(5)}",
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5)
        )
    )
}