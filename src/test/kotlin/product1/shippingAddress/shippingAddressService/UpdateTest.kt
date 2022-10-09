package product1.shippingAddress.shippingAddressService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.bson.types.ObjectId
import product1.fixture.shippingAddressPayload
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressRepository
import product1.shippingaddress.ShippingAddressService

class UpdateTest : BehaviorSpec({

    val shippingAddressRepository: ShippingAddressRepository = mockk()
    val shippingAddressService = ShippingAddressService(shippingAddressRepository)

    Given("배송지 업데이트") {
        val id = ObjectId.get()
        val companyId = ObjectId.get()
        val original = ShippingAddress.create(companyId, shippingAddressPayload()).copy(id = id)
        val payload = shippingAddressPayload {
            name = "수정배송지1"
            firstRecipient = "수정수신자1"
            secondRecipient = "수정수신자2"
            firstPhoneNumber = "수정휴대폰번호1"
            secondPhoneNumber = "수정휴대폰번호2"
            zipCode = "000000"
            line1 = "주소1"
            line2 = "주소2"
        }

        When("id가 존재하지 않는 경우") {
            clearAllMocks()
            val expected = Exception("존재하지 않는 배송지 입니다.")

            coEvery {
                shippingAddressRepository.findById(id)
            } returns null

            val result = shouldThrow<Exception> { shippingAddressService.update(id, companyId, payload) }

            Then("result.message = expected.message") {
                result.message shouldBe expected.message
            }
        }

        When("companyId가 다른 경우") {
            clearAllMocks()
            val expected = IllegalArgumentException("기업 번호가 일치하지 않습니다")

            coEvery {
                shippingAddressRepository.findById(id)
            } returns original

            val result =
                shouldThrow<IllegalArgumentException> { shippingAddressService.update(id, ObjectId.get(), payload) }

            Then("result.message = expected.message") {
                result.message shouldBe expected.message
            }
        }

        When("배송지 업데이트 하는 경우") {
            clearAllMocks()
            val expected = original.update(payload)

            coEvery {
                shippingAddressRepository.findById(id)
            } returns original
            coEvery {
                shippingAddressRepository.save(expected)
            } returns expected

            val result = shippingAddressService.update(id, companyId, payload)

            Then("result.id = expected.id") {
                result.id shouldBe expected.id
            }

            Then("result.companyId = expected.companyId") {
                result.companyId shouldBe expected.companyId
            }

            Then("result.name = expected.name") {
                result.name shouldBe expected.name
            }
        }
    }
})