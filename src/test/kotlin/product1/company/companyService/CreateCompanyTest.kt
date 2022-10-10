package product1.company.companyService

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import product1.company.Company
import product1.company.CompanyRepository
import product1.company.CompanyService
import product1.fixture.companyPayload

class CreateCompanyTest : BehaviorSpec({
    val companyRepository: CompanyRepository = mockk()
    val companyService = CompanyService(companyRepository)
    val payload = companyPayload {
        name = "조직"
        email = "xxx@xxx"
        phoneNumber = "00012341234"
        certificate = "증명서"
        address = "주소"
    }

    beforeTest {
        clearAllMocks()
    }

    Given("동일한 증명서인 경우") {
        Then("이미 가입한 증명서입니다. 예외 메세지 발생") {
            coEvery { companyRepository.existsByCertificate(payload.certificate) } returns true
            shouldThrowMessage("이미 가입한 증명서입니다.") {
                companyService.createCompany(payload)
            }
        }
    }

    Given("동일한 조직명인 경우") {
        Then("이미 가입한 조직명입니다. 예외 메세지 발생") {
            coEvery { companyRepository.existsByCertificate(payload.certificate) } returns false
            coEvery { companyRepository.existsByName(payload.name) } returns true
            shouldThrowMessage("이미 가입한 조직명입니다.") {
                companyService.createCompany(payload)
            }
        }
    }

    Given("조직 생성 성공") {
        Then("Company 타입의 객체를 반환") {
            val expected = Company.create(payload)
            coEvery { companyRepository.existsByCertificate(payload.certificate) } returns false
            coEvery { companyRepository.existsByName(payload.name) } returns false
            coEvery { companyRepository.save(expected) } returns expected
            val result = companyService.createCompany(payload)
            result shouldBe expected
        }
    }

})