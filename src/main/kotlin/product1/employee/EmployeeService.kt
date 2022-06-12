package product1.employee

import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import product1.company.Company
import product1.config.Constants

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {

    /**
     * 사원 생성
     * @param payload 사원 생성 필드
     */
    suspend fun create(companyId: ObjectId, payload: EmployeePayload) {
        val employee = Employee.create(companyId, payload)
        create(employee)
    }

    /**
     * 기업 관리자 생성
     */
    suspend fun createCompanyManager(company: Company) {
        val manager = company.createManager()
        create(manager)
    }

    /**
     * 직원 데이터 생성
     */
    private suspend fun create(employee: Employee) {
        require(!employeeRepository.existsByCompanyIdAndEmail(employee.companyId, employee.email)) { "이미 가입된 이메일입니다." }
        require(!employeeRepository.existsByCompanyIdAndName(employee.companyId, employee.name)) { "이미 가입된 이름입니다." }
        employeeRepository.save(employee)
    }
}

fun Company.createManager() = Employee(
    companyId = id!!,
    name = name,
    position = "기업관리자",
    email = email,
    emailVerified = emailVerified,
    phoneNumber = phoneNumber,
    phoneNumberVerified = false,
    password = "test1234!",
    permissions = listOf(EmployeePermission.PAYMENT, EmployeePermission.SHIPPING_ADDRESS),
    roles = listOf(Constants.COMPANY_MANAGER)
)