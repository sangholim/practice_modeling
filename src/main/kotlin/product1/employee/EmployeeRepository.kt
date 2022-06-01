package product1.employee

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface EmployeeRepository: CoroutineSortingRepository<Employee, ObjectId> {

    /**
     * 사원명 존재 여부
     * @param companyId 기업 번호
     * @param name 사원명
     */
    suspend fun existsByCompanyIdAndName(companyId:ObjectId, name: String): Boolean

    /**
     * 사원 이메일 존재 여부
     * @param companyId ObjectId
     * @param email String
     * @return Boolean
     */
    suspend fun existsByCompanyIdAndEmail(companyId: ObjectId, email:String): Boolean
}