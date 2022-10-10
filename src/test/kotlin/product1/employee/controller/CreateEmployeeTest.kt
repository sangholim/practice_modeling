package product1.employee.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import product1.employee.Employee
import product1.employee.EmployeeController
import product1.employee.EmployeeFacadeService
import product1.fixture.employeePayload

@WebFluxTest(controllers = [EmployeeController::class])
class CreateEmployeeTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    val employeeFacadeService: EmployeeFacadeService
) : BehaviorSpec({
    Given("직원 생성 API 요청") {
        val companyId = ObjectId.get()
        val uri = "/companies/$companyId/employees"
        When("uri 이 올바르지 않은 경우") {
            clearAllMocks()
            val payload = employeePayload()
            val invalidUri = "/companies/abcd/employees"
            val result = webTestClient.post().uri(invalidUri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .exchange()
            Then("expectStatus().isBadRequest") {
                result.expectStatus().isBadRequest
            }
        }

        When("payload 유효하지 않은 경우") {
            clearAllMocks()
            val payload = employeePayload()
            val result = webTestClient.post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .exchange()

            Then("expectStatus().isBadRequest") {
                result.expectStatus().isBadRequest
            }
            Then("expectBody().jsonPath: field.length() = 5") {
                result.expectBody().jsonPath("$.fields.length()").isEqualTo(5)
            }
        }

        When("직원 생성을 성공한 경우") {
            clearAllMocks()
            val payload = employeePayload {
                name = "직웝"
                position = "직위"
                email = "xx@xx.com"
                phoneNumber = "0001341234"
                password = "test1234!"
            }
            val entity = Employee.create(companyId, payload)

            coEvery {
                employeeFacadeService.createEmployee(companyId, payload)
            } returns entity

            val result = webTestClient.post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .exchange()

            Then("expectStatus().isCreated") {
                result.expectStatus().isCreated
            }

            Then("expectBody().isEmpty") {
                result.expectBody().isEmpty
            }
        }

    }
})