package product1.company.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import product1.company.Company
import product1.company.CompanyController
import product1.company.CompanyFacadeService
import product1.fixture.companyPayload

@WebFluxTest(controllers = [CompanyController::class])
class CreateCompanyTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val companyFacadeService: CompanyFacadeService
) : BehaviorSpec({
    Given("조직 생성 API 요청") {

        When("payload 필드 유효성 검사 실패") {
            clearAllMocks()
            val payload = companyPayload()
            val result = webTestClient.post()
                .uri("/companies")
                .bodyValue(payload)
                .exchange()

            Then("expectStatus.isBadRequest") {
                result.expectStatus().isBadRequest
            }
            Then("expectBody().jsonPath: field.length() = 5") {
                result.expectBody().jsonPath("$.fields.length()").isEqualTo(5)
            }
        }

        When("조직 생성 성공") {
            clearAllMocks()
            val payload = companyPayload {
                name = "조직"
                email = "xxx@xxx"
                phoneNumber = "00012341234"
                certificate = "증명서"
                address = "주소"
            }
            val company = Company.create(payload)
            coEvery {
                companyFacadeService.createCompany(payload)
            } returns company

            val result = webTestClient.post()
                .uri("/companies")
                .bodyValue(payload)
                .exchange()

            Then("expectStatus.isCreated") {
                result.expectStatus().isCreated
            }
            Then("expectBody().isEmpty") {
                result.expectBody().isEmpty
            }
        }
    }
})