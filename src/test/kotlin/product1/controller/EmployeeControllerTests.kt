package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import product1.employee.EmployeeController
import product1.employee.EmployeeService
import product1.fixture.CompanyFixture
import product1.fixture.EmployeeFixture

@WebFluxTest(EmployeeController::class)
class EmployeeControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var employeeService: EmployeeService

    @Test
    fun createEmployee() {
        val companyId = CompanyFixture.id
        val payload = EmployeeFixture.createEmployeePayload()
        coEvery {
            employeeService.create(companyId, payload)
        } returns Unit

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

        webTestClient.post().uri("/companies/$companyId/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .exchange()
            .expectStatus().isCreated
            .expectBody().isEmpty
    }
}