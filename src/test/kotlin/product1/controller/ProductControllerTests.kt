package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import kotlinx.coroutines.flow.asFlow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.fixture.ProductFixture
import product1.product.ProductController
import product1.product.ProductService
import product1.product.dto.ProductView

@WebFluxTest(ProductController::class)
class ProductControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var productService: ProductService


    @Test
    fun getProducts() {
        val products = ProductFixture.createProducts()
        coEvery {
            productService.getProducts()
        } returns products.asFlow()

        webTestClient.get()
            .uri("/products")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(ProductView::class.java)
    }

    @Test
    fun getProduct() {
        val product = ProductFixture.createProducts().first()
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