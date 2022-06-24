package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.fixture.ProductFixture
import product1.fixture.ProductOptionFixture
import product1.product.domain.ProductOption
import product1.product.domain.ProductOptionType
import product1.product.repository.ProductOptionRepository

@ExperimentalCoroutinesApi
class ProductOptionRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var productOptionRepository: ProductOptionRepository

    @BeforeEach
    fun setUp() = runTest {
        productOptionRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        val productId = ProductFixture.ID
        assert(save(productId, ProductOptionType.COMBINATION).variants == null)
        assert(save(productId, ProductOptionType.EXTRA).variants == null)
    }

    @Test
    fun findById() = runTest {
        val productId = ProductFixture.ID
        val id = save(productId, ProductOptionType.COMBINATION).id!!
        assert(productOptionRepository.findById(id) != null)
    }

    @Test
    fun delete() = runTest {
        val productId = ProductFixture.ID
        val id = save(productId, ProductOptionType.COMBINATION).id!!
        productOptionRepository.deleteById(id)
        assert(productOptionRepository.findById(id) == null)
    }

    private suspend fun save(productId: ObjectId, type: ProductOptionType): ProductOption {
        val payload = ProductOptionFixture.createPayloadByType(type)
        return productOptionRepository.save(ProductOption.create(productId, payload))
    }
}