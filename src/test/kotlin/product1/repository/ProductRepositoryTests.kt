package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.fixture.CompanyFixture
import product1.fixture.ProductFixture
import product1.product.domain.Product
import product1.product.ProductRepository

@ExperimentalCoroutinesApi
class ProductRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() = runTest {
        productRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        val companyId = CompanyFixture.id
        val product = save(companyId)
        assert(product.id != null)
    }

    private suspend fun save(companyId: ObjectId): Product {
        val payload = ProductFixture.createPayload()
        return productRepository.save(Product.create(companyId, payload))
    }
}