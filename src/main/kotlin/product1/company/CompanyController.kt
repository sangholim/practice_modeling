package product1.company

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/companies"])
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

    /**
     * 기업 조회
     * @param id 기업 번호
     */
    @GetMapping(value = ["/{id}"])
    @ResponseStatus(value = OK)
    suspend fun getCompany(@PathVariable id: ObjectId) =
        companyService.getById(id)
}