package product1.product.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import kotlinx.coroutines.flow.flowOf
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.product.ProductController
import product1.product.ProductService
import product1.product.dto.ProductSummaryView
import product1.product.fixture.ProductFixture

@WebFluxTest(controllers = [ProductController::class])
class GetProductsTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val productService: ProductService
) : BehaviorSpec({
    Given("상품 리스트 API 요청") {
        When("상품 리스트 조회 성공") {
            clearAllMocks()
            coEvery {
                productService.getProducts()
            } returns flowOf(ProductFixture.createProduct())

            val result = webTestClient.get()
                .uri("/products")
                .exchange()
            
            Then("expectStatus().isOk") {
                result.expectStatus().isOk
            }
            Then("expectBodyList(ProductSummaryView::class.java)") {
                result.expectBodyList(ProductSummaryView::class.java)
            }
        }
    }
})