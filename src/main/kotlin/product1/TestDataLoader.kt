package product1

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import product1.product.ProductRepository
import product1.product.fixture.ProductFixture
import product1.variant.VariantFixture
import product1.variant.VariantRepository

@Component
@Profile(value = ["dev"])
class TestDataLoader(
    private val productRepository: ProductRepository,
    private val variantRepository: VariantRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) = runBlocking {
        generateProducts()
        generateVariants()
    }

    private suspend fun generateProducts() {
        productRepository.deleteAll()
        productRepository.save(ProductFixture.createProduct())
    }

    private suspend fun generateVariants() {
        variantRepository.deleteAll()
        val variants = productRepository.findAll().toList().flatMap {
            VariantFixture.createVariants(it)
        }
        variantRepository.saveAll(variants).collect()
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