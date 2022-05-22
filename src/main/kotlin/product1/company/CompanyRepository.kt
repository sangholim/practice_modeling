package product1.company

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CompanyRepository : CoroutineCrudRepository<Company, ObjectId> {

    /**
     * 증명서로 등록된 회사 존재 여부
     * @param certificate 증명서
     */
    suspend fun existsByCertificate(certificate: String): Boolean
}