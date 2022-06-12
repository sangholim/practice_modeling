package product1.service

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import product1.company.CompanyPayload
import product1.company.CompanyRepository
import product1.company.CompanyService
import product1.company.CompanyView
import product1.fixture.CompanyFixture

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class CompanyServiceTests {

    @MockK
    lateinit var companyRepository: CompanyRepository

    @InjectMockKs
    lateinit var companyService: CompanyService

    private val payload: CompanyPayload = CompanyFixture.createCompanyPayload()

    private val view: CompanyView = CompanyFixture.createCompanyView()

    @BeforeEach
    fun setUp() = runTest {
        clearAllMocks()
    }

    @Test
    fun getById() = runTest {
        coEvery {
            companyRepository.findById(CompanyFixture.id)
        } returns CompanyFixture.createCompany(CompanyFixture.id)

        val result = companyService.getById(CompanyFixture.id)
        assertEquals(view, result)
    }

    @Test
    fun create() = runTest {
        coEvery {
            companyRepository.existsByCertificate(CompanyFixture.certificate)
        } returns false

        coEvery {
            companyRepository.existsByName(CompanyFixture.name)
        } returns false

        coEvery {
            companyRepository.save(CompanyFixture.createCompany())
        } returns CompanyFixture.createCompany(ObjectId.get())

        companyService.createCompany(payload)
    }
}