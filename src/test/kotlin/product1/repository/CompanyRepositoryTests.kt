package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
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

    @Test
    fun findAll() = runTest {
        save()
        assert(companyRepository.findAll().count() > 0)
    }

    @Test
    fun update() = runTest {
        val company = companyRepository.save(CompanyFixture.createCompany())
        val updateCompany = companyRepository.save(company.copy(name = "test123444"))
        assert(company.id == updateCompany.id && company.name != updateCompany.name)
    }

    @Test
    fun delete() = runTest {
        val company = companyRepository.save(CompanyFixture.createCompany())
        companyRepository.delete(company)
        assert(companyRepository.findById(company.id!!) == null)
    }

    @Test
    fun existsByCertificate() = runTest {
        save()
        assert(companyRepository.existsByCertificate(CompanyFixture.certificate))
    }
}