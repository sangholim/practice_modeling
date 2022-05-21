package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.company.CompanyRepository
import product1.fixture.CompanyFixture

@ExperimentalCoroutinesApi
class CompanyRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @BeforeEach
    fun setUp() = runTest {
        companyRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        assert(companyRepository.save(CompanyFixture.createCompany()).let { company ->
            company.id != null &&
                    company.email == CompanyFixture.email &&
                    company.name == CompanyFixture.name &&
                    company.phoneNumber == CompanyFixture.phoneNumber &&
                    company.certificate == CompanyFixture.certificate
        })

    }
}