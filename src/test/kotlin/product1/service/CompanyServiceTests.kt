package product1.service

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import product1.company.CompanyPayload
import product1.company.CompanyRepository
import product1.company.CompanyService
import product1.fixture.CompanyFixture

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class CompanyServiceTests {

    @MockK
    lateinit var companyRepository: CompanyRepository

    @InjectMockKs
    lateinit var companyService: CompanyService

    private val payload: CompanyPayload = CompanyFixture.createCompanyPayload()

    @BeforeEach
    fun setUp() = runTest {
        clearAllMocks()
    }

    @Test
    fun create() = runTest {
        coEvery {
            companyRepository.existsByCertificate(CompanyFixture.certificate)
        } returns false

        coEvery {
            companyRepository.save(CompanyFixture.createCompany())
        } returns CompanyFixture.createCompany(ObjectId.get())

        companyService.create(payload)
    }
}