package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.fixture.ProductDescriptionFixture
import product1.fixture.ProductFixture
import product1.product.domain.ProductDescription
import product1.product.repository.ProductDescriptionRepository

@ExperimentalCoroutinesApi
class ProductDescriptionTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var productDescriptionRepository: ProductDescriptionRepository

    @BeforeEach
    fun setUp() = runTest {
        productDescriptionRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        val productId = ProductFixture.ID
        assert(save(productId).id != null)
    }

    @Test
    fun findById() = runTest {
        val productId = ProductFixture.ID
        val id = save(productId).id!!
        assert(productDescriptionRepository.findById(id) != null)
    }

    @Test
    fun delete() = runTest {
        val productId = ProductFixture.ID
        val id = save(productId).id!!
        productDescriptionRepository.deleteById(id)
        assert(productDescriptionRepository.findById(id) == null)
    }

    private suspend fun save(productId: ObjectId): ProductDescription {
        val payload = ProductDescriptionFixture.createPayload()
        return productDescriptionRepository.save(ProductDescription.create(productId, payload))
    }
}