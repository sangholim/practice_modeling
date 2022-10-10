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
import product1.fixture.employeePayload

class CreateTest : BehaviorSpec({
    val employeeService: EmployeeService = mockk()
    val cartService: CartService = mockk(relaxed = true)
    val employeeFacadeService = EmployeeFacadeService(employeeService, cartService)
    val companyId = ObjectId.get()
    val employeeId = ObjectId.get()

    beforeTest {
        clearAllMocks()
    }

    Given("직원 생성하기") {
        Then("Employee 타입의 객체를 반환한다") {
            val payload = employeePayload {
                name = "직웝"
                position = "직위"
                email = "xx@xx.com"
                phoneNumber = "0001341234"
                password = "test1234!"
            }
            val expected = Employee.create(companyId, payload).copy(id = employeeId)

            coEvery {
                employeeService.create(companyId, payload)
            } returns expected
            coEvery {
                cartService.createCart(employeeId)
            } returns mockk(relaxed = true)

            val result = employeeFacadeService.createEmployee(companyId, payload)
            result shouldBe expected
        }
    }
})