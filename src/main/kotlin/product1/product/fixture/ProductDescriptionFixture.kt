package product1.product.fixture

import product1.TestDataLoader
import product1.product.domain.ProductDescription

object ProductDescriptionFixture {

    fun create() = ProductDescription(
        images = listOf(
            "http://main-image-${TestDataLoader.generateAlphabet(5)}",
            "http://image-${TestDataLoader.generateAlphabet(5)}",
            "http://image-${TestDataLoader.generateAlphabet(5)}",
            "http://image-${TestDataLoader.generateAlphabet(5)}",
            "http://image-${TestDataLoader.generateAlphabet(5)}"
        ),
        contents = listOf(
            "http://content-${TestDataLoader.generateAlphabet(5)}",
            "http://content-${TestDataLoader.generateAlphabet(5)}",
            "http://content-${TestDataLoader.generateAlphabet(5)}",
            "http://content-${TestDataLoader.generateAlphabet(5)}",
            "http://content-${TestDataLoader.generateAlphabet(5)}"
        )
    )
}