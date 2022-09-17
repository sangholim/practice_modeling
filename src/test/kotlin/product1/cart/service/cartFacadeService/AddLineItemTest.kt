package product1.cart.service.cartFacadeService

import io.mockk.clearAllMocks
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import product1.cart.CartFacadeService
import product1.cart.CartService
import product1.product.ProductService
import product1.variant.VariantService

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class AddLineItemTest {

    @MockK
    lateinit var cartService: CartService

    @MockK
    lateinit var productService: ProductService

    @MockK
    lateinit var variantService: VariantService

    @InjectMockKs
    lateinit var cartFacadeService: CartFacadeService

    @BeforeEach
    fun setUp() = runTest {
        clearAllMocks()
    }
}