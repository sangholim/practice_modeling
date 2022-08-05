package product1.service

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import product1.product.fixture.ProductFixture
import product1.variant.VariantFixture
import product1.variant.VariantRepository
import product1.variant.VariantService

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class VariantServiceTests {

    @MockK
    lateinit var variantRepository: VariantRepository

    @InjectMockKs
    lateinit var variantService: VariantService

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun getVariantByCode() = runTest {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product)
        val variant = variants.first()
        coEvery {
            variantRepository.findByProductId(product.id!!)
        } returns variants.asFlow()

        val result = variantService.getVariantByCode(product.id!!, variant.code)

        assert(variant == result)
    }
}