package product1.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import product1.company.CompanyController
import product1.company.CompanyService
import product1.company.CompanyView
import product1.fixture.CompanyFixture

@WebFluxTest(CompanyController::class)
class CompanyControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var companyService: CompanyService

    @Test
    fun createCompany() {
        val payload = CompanyFixture.createCompanyPayload()
        coEvery {
            companyService.create(payload)
        } returns Unit

        webTestClient.post().uri("/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(CompanyFixture.createInvalidCompanyPayload())
            .exchange()
            .expectStatus().isBadRequest

        webTestClient.post().uri("/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .exchange()
            .expectStatus().isCreated
            .expectBody().isEmpty
    }

    @Test
    fun getById() {
        coEvery {
            companyService.getById(CompanyFixture.id)
        } returns CompanyFixture.createCompanyView()
        webTestClient.get().uri("/companies/${CompanyFixture.id}")
            .exchange()
            .expectStatus().isOk
            .expectBody(CompanyView::class.java)
    }
}