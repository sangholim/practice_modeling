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
import product1.cart.CartService
import product1.cart.domain.Cart
import product1.employee.Employee
import product1.employee.EmployeeFacadeService
import product1.employee.EmployeeService
import product1.fixture.CompanyFixture
import product1.fixture.EmployeeFixture

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class EmployeeFacadeServiceTests {

    @MockK
    lateinit var employeeService: EmployeeService

    @MockK
    lateinit var cartService: CartService

    @InjectMockKs
    lateinit var employeeFacadeService: EmployeeFacadeService

    @BeforeEach
    fun setUp() = runTest {
        clearAllMocks()
    }

    @Test
    fun create() = runTest {
        val companyId = CompanyFixture.id
        val employeeId = EmployeeFixture.ID
        val payload = EmployeeFixture.createEmployeePayload()
        val model = Employee.create(companyId, payload).copy(id = employeeId)
        val cart = Cart.of(employeeId)
        coEvery {
            employeeService.create(companyId, payload)
        } returns model

        coEvery {
            cartService.createCart(employeeId)
        } returns cart

        val result = employeeFacadeService.createEmployee(companyId, payload)

        assert(model == result)
    }

}