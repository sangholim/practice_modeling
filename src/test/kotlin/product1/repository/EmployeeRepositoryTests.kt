package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.employee.Employee
import product1.employee.EmployeeRepository
import product1.fixture.CompanyFixture
import product1.fixture.EmployeeFixture

@ExperimentalCoroutinesApi
class EmployeeRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    @BeforeEach
    fun setUp() = runTest {
        employeeRepository.deleteAll()
    }

    @Test
    fun save() = runTest {
        val result = saveEmployee()
        assert(
            result.id != null &&
            result.permissions.isNotEmpty() &&
            result.roles.isNotEmpty()
        )
    }

    @Test
    fun findById() = runTest {
        val result = saveEmployee()
        assert(employeeRepository.findById(result.id!!) != null)
    }

    @Test
    fun existsByCompanyIdAndName() = runTest {
        saveEmployee()
        assert(employeeRepository.existsByCompanyIdAndName(CompanyFixture.id, EmployeeFixture.NAME))
    }

    @Test
    fun existsByCompanyIdAndEmail() = runTest {
        saveEmployee()
        assert(employeeRepository.existsByCompanyIdAndEmail(CompanyFixture.id, EmployeeFixture.EMAIL))
    }


    private suspend fun saveEmployee(): Employee {
        val employee = Employee.create(CompanyFixture.id, EmployeeFixture.createEmployeePayload())
        return employeeRepository.save(employee)
    }

}