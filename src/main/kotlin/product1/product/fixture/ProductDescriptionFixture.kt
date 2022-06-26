package product1.product.fixture

import product1.TestDataLoader
import product1.product.domain.ProductDescription

object ProductDescriptionFixture {

    fun create() = ProductDescription(
        images = listOf(
            "main-${TestDataLoader.generateAlphabet(5)}",
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5)
        ),
        contents = listOf(
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5),
            TestDataLoader.generateAlphabet(5)
        )
    )
}