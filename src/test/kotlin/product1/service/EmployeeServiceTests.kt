package product1.service

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import product1.employee.Employee
import product1.employee.EmployeeRepository
import product1.employee.EmployeeService
import product1.fixture.CompanyFixture
import product1.fixture.EmployeeFixture

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class EmployeeServiceTests {

    @MockK
    lateinit var employeeRepository: EmployeeRepository

    @InjectMockKs
    lateinit var employeeService: EmployeeService

    @BeforeEach
    fun setUp() = runTest {
        clearAllMocks()
    }

    @Test
    fun create() = runTest {
        val companyId = CompanyFixture.id
        val payload = EmployeeFixture.createEmployeePayload()
        val model = Employee.create(companyId, payload)

        coEvery {
            employeeRepository.existsByCompanyIdAndEmail(companyId, payload.email)
        } returns false

        coEvery {
            employeeRepository.existsByCompanyIdAndName(companyId, payload.name)
        } returns false

        coEvery {
            employeeRepository.save(model)
        } returns model.copy(id = EmployeeFixture.ID)

        employeeService.create(companyId, payload)

    }

}