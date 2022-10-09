package product1.shippingAddress.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.fixture.shippingAddressPayload
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressController
import product1.shippingaddress.ShippingAddressService
import product1.shippingaddress.ShippingAddressView
import java.time.Instant

@WebFluxTest(ShippingAddressController::class)
class GetShippingAddressesTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    val shippingAddressService: ShippingAddressService
) : BehaviorSpec({
    Given("배송지 조회 API 요청") {
        val id = ObjectId.get()
        val companyId = ObjectId.get()
        val date = Instant.now()
        val shippingAddresses = listOf(
            ShippingAddress.create(companyId, shippingAddressPayload()).copy(id = id, createAt = date)
        )
        When("API 요청 성공한 경우") {
            clearAllMocks()
            coEvery {
                shippingAddressService.getShippingAddresses(companyId)
            } returns shippingAddresses
            val result = webTestClient.get()
                .uri("/companies/$companyId/shipping-addresses")
                .exchange()
            Then("expectStatus.isOk") {
                result.expectStatus().isOk
            }
            Then("expectBodyList: ShippingAddressView") {
                result.expectBodyList(ShippingAddressView::class.java)
            }
        }
    }
})