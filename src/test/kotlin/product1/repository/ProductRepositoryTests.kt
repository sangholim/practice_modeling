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
        val product = save(CompanyFixture.id)
        assert(product.id != null && product.description == null && product.options == null)
    }

    @Test
    fun findById() = runTest {
        val id = save(CompanyFixture.id).id!!
        assert(productRepository.findById(id) != null)
    }

    @Test
    fun delete() = runTest {
        val id = save(CompanyFixture.id).id!!
        productRepository.deleteById(id)
        assert(productRepository.findById(id) == null)
    }

    private suspend fun save(companyId: ObjectId): Product = productRepository.save(
        Product.create(companyId, ProductFixture.createPayload())
    )

}