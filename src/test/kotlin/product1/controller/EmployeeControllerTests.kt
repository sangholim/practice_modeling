package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import product1.employee.Employee
import product1.employee.EmployeeController
import product1.employee.EmployeeFacadeService
import product1.employee.EmployeeService
import product1.fixture.CompanyFixture
import product1.fixture.EmployeeFixture

@WebFluxTest(EmployeeController::class)
class EmployeeControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var employeeFacadeService: EmployeeFacadeService

    @Test
    fun createEmployee() {
        val companyId = CompanyFixture.id
        val payload = EmployeeFixture.createEmployeePayload()
        val employee = Employee.create(companyId, payload)
        coEvery {
            employeeFacadeService.createEmployee(companyId, payload)
        } returns employee

        webTestClient.post().uri("/companies/abcd/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(EmployeeFixture.createInvalidEmployeePayload())
            .exchange()
            .expectStatus().isBadRequest

        webTestClient.post().uri("/companies/$companyId/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(EmployeeFixture.createInvalidEmployeePayload())
            .exchange()
            .expectStatus().isBadRequest
            .expectBody().jsonPath("$.fields.length()").isEqualTo(5)

        webTestClient.post().uri("/companies/$companyId/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .exchange()
            .expectStatus().isCreated
            .expectBody().isEmpty
    }
}