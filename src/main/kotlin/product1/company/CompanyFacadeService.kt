package product1.company

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import product1.employee.EmployeeService

@Service
class CompanyFacadeService(
    private val companyService: CompanyService,
    private val employeeService: EmployeeService
) {
    /**
     * 기업 생성, 기업 관리자 계정 생성
     */
    suspend fun createCompany(payload: CompanyPayload): Company =
        companyService.createCompany(payload).apply {
            employeeService.createCompanyManager(this)
        }

    /**
     * 기업 정보 조회
     * @param id 기업 번호
     */
    suspend fun getById(id: ObjectId) =
        companyService.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}
