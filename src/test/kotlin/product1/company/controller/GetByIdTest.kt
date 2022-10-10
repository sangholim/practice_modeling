package product1.company.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.server.ResponseStatusException
import product1.company.*
import product1.fixture.companyPayload

@WebFluxTest(controllers = [CompanyController::class])
class GetByIdTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val companyFacadeService: CompanyFacadeService
) : BehaviorSpec({
    Given("조직 조회 API 요청") {
        val id = ObjectId.get()
        When("조직이 없는 경우") {
            clearAllMocks()
            coEvery {
                companyFacadeService.getById(id)
            } throws ResponseStatusException(HttpStatus.NOT_FOUND)

            val result = webTestClient.get()
                .uri("/companies/$id")
                .exchange()

            Then("expectStatus.isNotFound") {
                result.expectStatus().isNotFound
            }
        }

        When("조직 조회 성공") {
            clearAllMocks()
            val payload = companyPayload {
                name = "조직"
                email = "xxx@xxx"
                phoneNumber = "00012341234"
                certificate = "증명서"
                address = "주소"
            }
            val company = Company.create(payload).copy(id = id)
            coEvery {
                companyFacadeService.getById(id)
            } returns company

            val result = webTestClient.get()
                .uri("/companies/$id")
                .exchange()

            Then("expectStatus.isOk") {
                result.expectStatus().isOk
            }
            Then("expectBody<CompanyView>()") {
                result.expectBody<CompanyView>()
            }
        }
    }
})