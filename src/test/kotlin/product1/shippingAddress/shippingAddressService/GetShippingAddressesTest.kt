package product1.shippingAddress.shippingAddressService

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import org.bson.types.ObjectId
import product1.fixture.shippingAddressPayload
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressRepository
import product1.shippingaddress.ShippingAddressService

class GetShippingAddressesTest : BehaviorSpec({

    val shippingAddressRepository: ShippingAddressRepository = mockk()
    val shippingAddressService = ShippingAddressService(shippingAddressRepository)

    Given("배송지 조회하기") {
        val size = 10
        val companyId = ObjectId.get()
        val shippingAddresses = List(size) {
            ShippingAddress.create(companyId, shippingAddressPayload())
        }
        When("배송지 조회하기 성공한 경우") {
            clearAllMocks()
            coEvery {
                shippingAddressRepository.findByCompanyId(companyId)
            } returns shippingAddresses.asFlow()
            val result = shippingAddressService.getShippingAddresses(companyId)
            Then("result.size = size") {
                result.size shouldBe size
            }
        }
    }
})