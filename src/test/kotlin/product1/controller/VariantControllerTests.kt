package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.product.fixture.ProductFixture
import product1.variant.Variant
import product1.variant.VariantController
import product1.variant.VariantFixture
import product1.variant.VariantService

@WebFluxTest(VariantController::class)
class VariantControllerTests {


    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var variantService: VariantService

    @Test
    fun getVariantByCode()  {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product)
        val variant = variants.first()
        coEvery {
            variantService.getVariantByCode(product.id!!, variant.code)
        } returns variant

        webTestClient.get().uri("/products/${product.id}/variants/code/${variant.code}")
            .exchange()
            .expectStatus().isOk
            .expectBody(Variant::class.java)
    }
}