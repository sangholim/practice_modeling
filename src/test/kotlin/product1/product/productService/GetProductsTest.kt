package product1.product.productService

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import product1.product.ProductRepository
import product1.product.ProductService
import product1.product.fixture.ProductFixture

class GetProductsTest : BehaviorSpec({
    val productRepository: ProductRepository = mockk()
    val productService = ProductService(productRepository)
    val products = listOf(ProductFixture.createProduct())

    beforeTest {
        clearAllMocks()
    }

    Given("상품 리스트 조회") {
        Then("Flow<Product> 타입의 객체를 반환한다") {
            coEvery { productRepository.findAll() } returns products.asFlow()
            val result = productService.getProducts()
            result.toList() shouldBe products
        }
    }
})