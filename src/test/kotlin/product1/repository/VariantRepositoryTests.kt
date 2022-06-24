package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.fixture.ProductOptionFixture
import product1.fixture.VariantFixture
import product1.product.domain.Variant
import product1.product.repository.VariantRepository

@ExperimentalCoroutinesApi
class VariantRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var variantRepository: VariantRepository

    @BeforeEach
    fun setUp() = runTest {
        variantRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        val productOptionId = ProductOptionFixture.ID
        assert(save(productOptionId).id != null)
    }

    @Test
    fun findById() = runTest {
        val productOptionId = ProductOptionFixture.ID
        val id = save(productOptionId).id!!
        assert(variantRepository.findById(id) != null)
    }

    @Test
    fun delete() = runTest {
        val productOptionId = ProductOptionFixture.ID
        val id = save(productOptionId).id!!
        variantRepository.deleteById(id)
        assert(variantRepository.findById(id) == null)
    }

    private suspend fun save(productOptionId: ObjectId): Variant {
        val payload = VariantFixture.createPayload()
        return variantRepository.save(Variant.create(productOptionId, payload))
    }

}