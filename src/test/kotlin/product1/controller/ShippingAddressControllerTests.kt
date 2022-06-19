package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.fixture.CompanyFixture
import product1.fixture.ShippingAddressFixture
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressController
import product1.shippingaddress.ShippingAddressService
import product1.shippingaddress.ShippingAddressView

@WebFluxTest(ShippingAddressController::class)
class ShippingAddressControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var shippingAddressService: ShippingAddressService

    @Test
    fun getShippingAddresses() {
        val companyId = CompanyFixture.id
        coEvery {
            shippingAddressService.getShippingAddresses(companyId)
        } returns listOf(
            ShippingAddressFixture.createShippingAddress(companyId)
        )

        webTestClient.get()
            .uri("/companies/${CompanyFixture.id}/shipping-addresses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(ShippingAddressView::class.java)
    }

    @Test
    fun createShippingAddress() {
        val companyId = CompanyFixture.id
        val payload = ShippingAddressFixture.createPayload()
        coEvery {
            shippingAddressService.create(companyId, payload)
        } returns ShippingAddress.create(companyId, payload)

        webTestClient.post()
            .uri("/companies/${companyId}/shipping-addresses")
            .bodyValue(ShippingAddressFixture.createInvalidParams())
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .jsonPath("$.fields.length()").isEqualTo(7)

        webTestClient.post()
            .uri("/companies/${companyId}/shipping-addresses")
            .bodyValue(ShippingAddressFixture.createInvalidRequiredParams())
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .jsonPath("$.fields.length()").isEqualTo(5)

        webTestClient.post()
            .uri("/companies/${companyId}/shipping-addresses")
            .bodyValue(payload)
            .exchange()
            .expectStatus().isCreated
            .expectBody().isEmpty
    }

    @Test
    fun updateShippingAddress() {
        val id = ShippingAddressFixture.id
        val companyId = CompanyFixture.id
        val payload = ShippingAddressFixture.createPayload()

        coEvery {
            shippingAddressService.update(id, companyId, payload)
        } returns ShippingAddressFixture.createShippingAddress(companyId).copy(id = id)

        webTestClient.put()
            .uri("/companies/${CompanyFixture.id}/shipping-addresses/$id")
            .bodyValue(ShippingAddressFixture.createInvalidParams())
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .jsonPath("$.fields.length()").isEqualTo(7)

        webTestClient.put()
            .uri("/companies/${CompanyFixture.id}/shipping-addresses/$id")
            .bodyValue(ShippingAddressFixture.createInvalidRequiredParams())
            .exchange()
            .expectStatus().isBadRequest
            .expectBody()
            .jsonPath("$.fields.length()").isEqualTo(5)

        webTestClient.put()
            .uri("/companies/${CompanyFixture.id}/shipping-addresses/$id")
            .bodyValue(payload)
            .exchange()
            .expectStatus().isNoContent
            .expectBody().isEmpty
    }

    @Test
    fun deleteShippingAddress() {
        val id = ShippingAddressFixture.id
        val companyId = CompanyFixture.id
        coEvery {
            shippingAddressService.delete(id, companyId)
        } returns Unit

        webTestClient.delete()
            .uri("/companies/${CompanyFixture.id}/shipping-addresses/$id")
            .exchange()
            .expectStatus().isNoContent
            .expectBody().isEmpty
    }
}