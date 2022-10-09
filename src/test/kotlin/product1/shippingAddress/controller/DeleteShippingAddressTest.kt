package product1.shippingAddress.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.shippingaddress.ShippingAddressController
import product1.shippingaddress.ShippingAddressService

@WebFluxTest(ShippingAddressController::class)
class DeleteShippingAddressTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    val shippingAddressService: ShippingAddressService
) : BehaviorSpec({
    Given("배송지 삭제 API 요청") {
        val id = ObjectId.get()
        val companyId = ObjectId.get()
        val uri = "/companies/$companyId/shipping-addresses/$id"

        When("배송지 삭제 성공") {
            clearAllMocks()
            coEvery {
                shippingAddressService.delete(id, companyId)
            } returns Unit

            val result = webTestClient.delete()
                .uri(uri)
                .exchange()

            Then("expectStatus.isNoContent") {
                result.expectStatus().isNoContent
            }
            Then("expectBody() is empty") {
                result.expectBody().isEmpty
            }
        }
    }
})