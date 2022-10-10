package product1.employee.employeeFacadeService

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.bson.types.ObjectId
import product1.cart.CartService
import product1.employee.Employee
import product1.employee.EmployeeFacadeService
import product1.employee.EmployeeService
import product1.fixture.EmployeeFixture

class CreateTest : BehaviorSpec({
    val employeeService: EmployeeService = mockk()
    val cartService: CartService = mockk(relaxed = true)
    val employeeFacadeService = EmployeeFacadeService(employeeService, cartService)
    val companyId = ObjectId.get()
    val employeeId = EmployeeFixture.ID

    beforeTest {
        clearAllMocks()
    }

    Given("직원 생성하기") {
        Then("Employee 타입의 객체를 반환한다") {
            val payload = EmployeeFixture.createEmployeePayload()
            val expected = Employee.create(companyId, payload).copy(id = employeeId)

            coEvery {
                employeeService.create(companyId, payload)
            } returns expected
            coEvery {
                cartService.createCart(employeeId)
            } returns mockk(relaxed = true)

            val result = employeeFacadeService.createEmployee(companyId, payload)
            result shouldBe  expected
        }
    }
})