package product1.service

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import product1.product.fixture.ProductFixture
import product1.product.ProductRepository
import product1.product.ProductService

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class ProductServiceTests {

    @MockK
    lateinit var productRepository: ProductRepository

    @InjectMockKs
    lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun getProducts() = runTest {
        val lists = ProductFixture.createProducts()
        coEvery {
            productRepository.findAll()
        } returns lists.asFlow()

        val result = productService.getProducts()
        assert(result.count() == lists.count())
    }

}