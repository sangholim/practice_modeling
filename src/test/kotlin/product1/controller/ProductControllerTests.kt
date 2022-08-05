package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.product.fixture.ProductFixture
import product1.product.ProductController
import product1.product.ProductService
import product1.product.dto.ProductSummaryView
import product1.product.dto.ProductView

@WebFluxTest(ProductController::class)
class ProductControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var productService: ProductService


    @Test
    fun getProducts() {
        val products = ProductFixture.createProduct()
        coEvery {
            productService.getProducts()
        } returns flowOf(products)

        webTestClient.get()
            .uri("/products")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(ProductSummaryView::class.java)
    }

    @Test
    fun getProduct() {
        val product = ProductFixture.createProduct()
        coEvery {
            productService.getProduct(product.id!!)
        } returns product

        webTestClient.get()
            .uri("/products/${product.id}")
            .exchange()
            .expectStatus().isOk
            .expectBody(ProductView::class.java)
    }

}