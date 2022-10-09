package product1.shippingAddress.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.fixture.shippingAddressPayload
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressController
import product1.shippingaddress.ShippingAddressService

@WebFluxTest(ShippingAddressController::class)
class UpdateShippingAddressTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    val shippingAddressService: ShippingAddressService
) : BehaviorSpec({
    Given("배송지 수정 API 요청") {
        val id = ObjectId.get()
        val companyId = ObjectId.get()
        val uri = "/companies/$companyId/shipping-addresses/$id"

        When("payload 필드 유효성 검사 실패") {
            clearAllMocks()
            val payload = shippingAddressPayload {
                name = RandomStringUtils.randomAlphabetic(30)
                firstRecipient = RandomStringUtils.randomAlphabetic(30)
                secondRecipient = RandomStringUtils.randomAlphabetic(30)
                firstPhoneNumber = RandomStringUtils.randomNumeric(30)
                secondPhoneNumber = RandomStringUtils.randomNumeric(30)
                zipCode = RandomStringUtils.randomNumeric(30)
                line1 = ""
                line2 = ""
            }
            val result = webTestClient.put()
                .uri(uri)
                .bodyValue(payload)
                .exchange()

            Then("expectStatus.isBadRequest") {
                result.expectStatus().isBadRequest
            }
            Then("expectBody().jsonPath: field.length() = 7") {
                result.expectBody().jsonPath("$.fields.length()").isEqualTo(7)
            }
        }

        When("배송지 수정 성공") {
            clearAllMocks()
            val payload = shippingAddressPayload {
                name = "배송지"
                firstRecipient = "수신자"
                secondRecipient = "수신자"
                firstPhoneNumber = "0001234134"
                secondPhoneNumber = "00012341234"
                zipCode = "000000"
                line1 = "주소1"
                line2 = "주소2"
            }
            val expected = ShippingAddress.create(companyId, shippingAddressPayload())

            coEvery {
                shippingAddressService.update(id, companyId, payload)
            } returns expected

            val result = webTestClient.put()
                .uri(uri)
                .bodyValue(payload)
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