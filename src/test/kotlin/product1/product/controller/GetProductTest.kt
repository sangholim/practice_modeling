package product1.product.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import product1.product.ProductController
import product1.product.ProductService
import product1.product.dto.ProductView
import product1.product.fixture.ProductFixture

@WebFluxTest(controllers = [ProductController::class])
class GetProductTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val productService: ProductService
) : BehaviorSpec({
    Given("상품 조회 API 요청") {
        val product = ProductFixture.createProduct()
        val uri = "/products/${product.id!!}"

        When("uri 이 올바르지 않은 경우") {
            clearAllMocks()
            val invalidUri = "/products/invalid"
            val result = webTestClient.get()
                .uri(invalidUri)
                .exchange()
            Then("expectStatus().isBadRequest") {
                result.expectStatus().isBadRequest
            }
        }

        When("상품 조회 성공한 경우") {
            clearAllMocks()
            coEvery {
                productService.getProduct(product.id!!)
            } returns product

            val result = webTestClient.get()
                .uri(uri)
                .exchange()

            Then("expectStatus().isOk") {
                result.expectStatus().isOk
            }

            Then("expectBody<ProductView>()") {
                result.expectBody<ProductView>()
            }
        }
    }
})