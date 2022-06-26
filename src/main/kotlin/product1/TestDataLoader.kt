package product1

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import product1.product.ProductRepository
import product1.product.fixture.ProductFixture

@Component
@Profile(value = ["dev"])
class TestDataLoader(
    private val productRepository: ProductRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) = runBlocking {
        generateProducts()
    }

    private suspend fun generateProducts() {
        productRepository.deleteAll()
        productRepository.saveAll(ProductFixture.createProducts()).collect()
    }

    companion object {
        fun generateAlphabet(length: Int): String = List(length) {
            (('a'..'z') + ('A'..'Z')).random()
        }.joinToString("")

        fun generateNumber(length: Int): String = List(length) {
            ('0'..'9').random()
        }.joinToString("")
    }

}