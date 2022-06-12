package product1.company

import org.springframework.stereotype.Service
import product1.employee.EmployeeService

@Service
class CompanyFacadeService(
    private val companyService: CompanyService,
    private val employeeService: EmployeeService
) {
    /**
     * 기업 생성, 기업 관리자 계정 생성
     */
    suspend fun createCompany(payload: CompanyPayload) {
        // 기업 생성
        val company = companyService.createCompany(payload)
        // 기업 관리자 생성
        employeeService.createCompanyManager(company)
    }
}
