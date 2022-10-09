package product1.shippingAddress.shippingAddressService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.bson.types.ObjectId
import product1.fixture.shippingAddressPayload
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressRepository
import product1.shippingaddress.ShippingAddressService

class DeleteTest : BehaviorSpec({

    val shippingAddressRepository: ShippingAddressRepository = mockk()
    val shippingAddressService = ShippingAddressService(shippingAddressRepository)

    Given("배송지 제거") {
        val id = ObjectId.get()
        val companyId = ObjectId.get()
        val original = ShippingAddress.create(companyId, shippingAddressPayload()).copy(id = id)

        When("id가 존재하지 않는 경우") {
            clearAllMocks()
            val expected = Exception("존재하지 않는 배송지 입니다.")

            coEvery {
                shippingAddressRepository.findById(id)
            } returns null

            val result = shouldThrow<Exception> { shippingAddressService.delete(id, companyId) }

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
                shouldThrow<IllegalArgumentException> { shippingAddressService.delete(id, ObjectId.get()) }

            Then("result.message = expected.message") {
                result.message shouldBe expected.message
            }
        }

        When("배송지 삭제 하는 경우") {
            clearAllMocks()

            coEvery {
                shippingAddressRepository.findById(id)
            } returns original
            coEvery {
                shippingAddressRepository.deleteById(id)
            } returns Unit

            val result = shippingAddressService.delete(id, companyId)

            Then("Unit 타입의 객체를 반환한다.") {
                result.shouldBeSameInstanceAs(Unit)
            }
        }
    }
})