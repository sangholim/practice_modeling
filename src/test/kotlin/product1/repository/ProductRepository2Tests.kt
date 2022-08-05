package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.product.ProductRepository
import product1.product.fixture.ProductFixture

@ExperimentalCoroutinesApi
class ProductRepository2Tests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() = runTest {
        productRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        val product = productRepository.save(ProductFixture.createProduct())
        assert(product.id != null)
    }

}