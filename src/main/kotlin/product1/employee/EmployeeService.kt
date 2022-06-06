package product1.employee

import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {

    /**
     * 사원 생성
     * @param payload 사원 생성 필드
     */
    suspend fun create(companyId: ObjectId, payload: EmployeePayload) {
        require(!employeeRepository.existsByCompanyIdAndEmail(companyId, payload.email)) { "이미 가입된 이메일입니다." }
        require(!employeeRepository.existsByCompanyIdAndName(companyId, payload.name)) { "이미 가입된 이름입니다." }
        employeeRepository.save(Employee.create(companyId, payload))
    }
}