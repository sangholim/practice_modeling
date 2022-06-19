package product1.service

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import product1.fixture.CompanyFixture
import product1.fixture.ShippingAddressFixture
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressRepository
import product1.shippingaddress.ShippingAddressService

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class ShippingAddressServiceTests {

    @MockK
    lateinit var shippingAddressRepository: ShippingAddressRepository

    @InjectMockKs
    lateinit var shippingAddressService: ShippingAddressService

    @BeforeEach
    fun setUp() = runTest {
        clearAllMocks()
    }

    @Test
    fun create() = runTest {
        val companyId = CompanyFixture.id
        val payload = ShippingAddressFixture.createPayload()
        coEvery {
            shippingAddressRepository.findByCompanyId(companyId)
        } returns flow {
            ShippingAddress.create(companyId, ShippingAddressFixture.createPayload())
        }
        val shippingAddress = ShippingAddress.create(companyId, payload)
        coEvery {
            shippingAddressRepository.save(shippingAddress)
        } returns shippingAddress.copy(id = ShippingAddressFixture.id)
        val result = shippingAddressService.create(companyId, payload)
        assert(result.id == ShippingAddressFixture.id)
    }

    @Test
    fun update() = runTest {
        val id = ShippingAddressFixture.id
        val companyId = CompanyFixture.id
        val payload = ShippingAddressFixture.createPayload()

        val original = ShippingAddress.create(companyId, ShippingAddressFixture.createPayload()).copy(id = id)
        coEvery {
            shippingAddressRepository.findById(id)
        } returns original

        val update = original.update(payload)
        coEvery {
            shippingAddressRepository.save(update)
        } returns update

        val result = shippingAddressService.update(id, companyId, payload)
        assert(result.id == id && result.companyId == companyId && original.name != result.name)
    }

    @Test
    fun delete() = runTest {
        val id = ShippingAddressFixture.id
        val companyId = CompanyFixture.id
        coEvery {
            shippingAddressRepository.findById(id)
        } returns ShippingAddressFixture.createShippingAddress(companyId).copy(id = id)

        coEvery {
            shippingAddressRepository.deleteById(id)
        } returns Unit

        shippingAddressService.delete(id, companyId)
    }

    @Test
    fun getShippingAddresses() = runTest {
        val companyId = CompanyFixture.id
        coEvery {
            shippingAddressRepository.findByCompanyId(companyId)
        } returns flowOf(ShippingAddressFixture.createShippingAddress(companyId))

        val result = shippingAddressService.getShippingAddresses(companyId)
        assert(result.count { it.companyId == companyId } > 0)
    }
}