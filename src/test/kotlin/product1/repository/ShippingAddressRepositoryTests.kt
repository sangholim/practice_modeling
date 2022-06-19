package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.fixture.CompanyFixture
import product1.fixture.ShippingAddressFixture
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressRepository

@ExperimentalCoroutinesApi
class ShippingAddressRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var shippingAddressRepository: ShippingAddressRepository

    @BeforeEach
    fun setUp() = runTest {
        shippingAddressRepository.deleteAll()
    }

    @Test
    fun create() = runTest {
        val payload = ShippingAddressFixture.createPayload()
        val shippingAddress = ShippingAddress.create(CompanyFixture.id, payload)
        val result = shippingAddressRepository.save(shippingAddress)
        assert(result.id != null)
    }

    @Test
    fun update() = runTest {
        val payload = ShippingAddressFixture.createPayload()
        val shippingAddress = ShippingAddress.create(CompanyFixture.id, payload)
        val new = shippingAddressRepository.save(shippingAddress)
        assert(new.id != null)

        val updateShippingAddress = new.update(ShippingAddressFixture.createPayload())
        val update = shippingAddressRepository.save(updateShippingAddress)

        assert(
            new.id == update.id &&
                    new.companyId == update.companyId &&
                    new.name != update.name &&
                    new.firstPhoneNumber != update.firstPhoneNumber
        )
    }

    @Test
    fun findByCompanyId() = runTest {
        val companyId = CompanyFixture.id
        val payload = ShippingAddressFixture.createPayload()
        val shippingAddress = ShippingAddress.create(companyId, payload)
        val new = shippingAddressRepository.save(shippingAddress)
        assert(shippingAddressRepository.findByCompanyId(companyId).filter { it.id == new.id }.count() == 1)
    }

    @Test
    fun delete() = runTest {
        val companyId = CompanyFixture.id
        val payload = ShippingAddressFixture.createPayload()
        val shippingAddress = ShippingAddress.create(companyId, payload)
        val new = shippingAddressRepository.save(shippingAddress)
        shippingAddressRepository.delete(new)
        assert(shippingAddressRepository.findByCompanyId(companyId).count() == 0)
    }
}