package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.server.ResponseStatusException
import product1.product.fixture.ProductFixture
import product1.variant.*

@WebFluxTest(VariantController::class)
class VariantControllerTests {


    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var variantService: VariantService

    @Test
    fun getVariantNotFound() {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product)
        val variant = variants.first().copy(id = ObjectId.get())
        val code = "dd"
        coEvery {
            variantService.getVariantByCode(product.id!!, code)
        } throws ResponseStatusException(HttpStatus.NOT_FOUND)

        webTestClient
            .get()
            .uri { builder ->
                builder
                    .queryParam("code", code)
                    .queryParam("count", variant.stock)
                    .path("/products/${product.id}/variants/query")
                    .build()
            }
            .exchange()
            .expectStatus().isNotFound

    }

    @Test
    fun getVariant() {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product)
        val variant = variants.first().copy(id = ObjectId.get())
        coEvery {
            variantService.getVariantByCode(product.id!!, variant.code)
        } returns variant

        webTestClient
            .get()
            .uri { builder ->
                builder
                    .queryParam("code", variant.code)
                    .queryParam("count", variant.stock + 1)
                    .path("/products/${product.id}/variants/query")
                    .build()

            }
            .exchange()
            .expectStatus().isBadRequest

        webTestClient
            .get()
            .uri { builder ->
                builder
                    .queryParam("code", variant.code)
                    .queryParam("count", variant.stock)
                    .path("/products/${product.id}/variants/query")
                    .build()

            }
            .exchange()
            .expectStatus().isOk
            .expectBody(VariantView::class.java)
    }
}