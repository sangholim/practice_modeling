package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.variant.VariantFixture
import product1.variant.VariantRepository

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
        val variant = variantRepository.save(VariantFixture.createVariant())
        assert(variant.id != null)
    }

    @Test
    fun findAll() = runTest {
        val variants = VariantFixture.createVariants(8)
        variantRepository.saveAll(variants).collect()
        assert(variantRepository.findAll().count() > 0)
    }
}