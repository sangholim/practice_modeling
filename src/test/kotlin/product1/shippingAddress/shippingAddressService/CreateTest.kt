package product1.shippingAddress.shippingAddressService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import org.bson.types.ObjectId
import product1.fixture.shippingAddressPayload
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressRepository
import product1.shippingaddress.ShippingAddressService

class CreateTest : BehaviorSpec({

    val shippingAddressRepository: ShippingAddressRepository = mockk()
    val shippingAddressService = ShippingAddressService(shippingAddressRepository)

    Given("배송지 생성") {
        val payload = shippingAddressPayload {
            name = "배송지1"
            firstRecipient = "수신자1"
            secondRecipient = "수신자2"
            firstPhoneNumber = "휴대폰번호1"
            secondPhoneNumber = "휴대폰번호2"
            zipCode = "000000"
            line1 = "주소1"
            line2 = "주소2"
        }
        val companyId = ObjectId.get()

        When("배송지 갯수가 20개 이상인 경우") {
            clearAllMocks()
            val expected = IllegalArgumentException("배송지 최대 갯수 초과하였습니다.")

            coEvery {
                shippingAddressRepository.findByCompanyId(companyId)
            } returns List(20) {
                ShippingAddress.create(companyId, payload)
            }.asFlow()

            val result = shouldThrow<IllegalArgumentException> {
                shippingAddressService.create(companyId, payload)
            }
            Then("result.message = expected.message") {
                result.message shouldBe expected.message
            }
        }

        When("배송지 등록한 경우") {
            clearAllMocks()
            val expected = ShippingAddress.create(companyId, payload)

            coEvery {
                shippingAddressRepository.findByCompanyId(companyId)
            } answers { emptyFlow() }

            coEvery {
                shippingAddressRepository.save(expected)
            } returns expected
            val result = shippingAddressService.create(companyId, payload)

            Then("result.name = expected.name") {
                result.name shouldBe expected.name
            }

            Then("result.companyId = companyId") {
                result.name shouldBe expected.name
                result.companyId shouldBe companyId
            }
        }
    }
})