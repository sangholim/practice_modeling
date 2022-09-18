package product1.employee.employeeService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import product1.employee.Employee
import product1.employee.EmployeeRepository
import product1.employee.EmployeeService
import product1.fixture.CompanyFixture
import product1.fixture.EmployeeFixture
import java.lang.IllegalArgumentException

class CreateTest : BehaviorSpec({
    val employeeRepository: EmployeeRepository = mockk()
    val employeeService = EmployeeService(employeeRepository)
    val companyId = CompanyFixture.id
    val payload = EmployeeFixture.createEmployeePayload()

    beforeTest {
        clearAllMocks()
    }

    Given("이미 가입된 이메일인 경우") {
        Then("throw IllegalArgumentException('이미 가입된 이메일입니다.')") {
            coEvery { employeeRepository.existsByCompanyIdAndEmail(any(), payload.email) } returns true
            val result = shouldThrow<IllegalArgumentException> { employeeService.create(companyId, payload) }
            result.message shouldBe  "이미 가입된 이메일입니다."
        }
    }

    Given("이미 가입된 이름인 경우") {
        Then("throw IllegalArgumentException('이미 가입된 이름입니다.')") {
            coEvery { employeeRepository.existsByCompanyIdAndEmail(companyId, payload.email) } returns false
            coEvery { employeeRepository.existsByCompanyIdAndName(companyId, payload.name) } returns true
            val result = shouldThrow<IllegalArgumentException> { employeeService.create(companyId, payload) }
            result.message shouldBe  "이미 가입된 이름입니다."
        }
    }

    Given("회원 생성 성공") {
        val expected = Employee.create(companyId, payload)
        Then("Employee 타입의 객체를 반환한다") {
            coEvery { employeeRepository.existsByCompanyIdAndEmail(companyId, payload.email) } returns false
            coEvery { employeeRepository.existsByCompanyIdAndName(companyId, payload.name) } returns false
            coEvery { employeeRepository.save(expected) } returns expected
            val result = employeeService.create(companyId, payload)
            result shouldBe expected
        }
    }
})