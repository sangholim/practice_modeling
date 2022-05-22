package product1.company

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/company"])
class CompanyController(
    private val companyService: CompanyService
) {

    /**
     * 기업 생성
     * @param payload 기업 생성 필드 데이터
     */
    @PostMapping
    @ResponseStatus(value = CREATED)
    suspend fun createCompany(@Valid @RequestBody payload: CompanyPayload) =
        companyService.create(payload)
}