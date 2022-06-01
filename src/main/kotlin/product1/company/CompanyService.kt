package product1.company

import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    /**
     * 기업 정보 생성
     */
    suspend fun create(payload: CompanyPayload) {
        // 기업 이메일, 휴대폰 번호 인증 필요 !! , 현재는 mock 처리
        if (!isVerified(payload.email, payload.phoneNumber)) throw Exception("인증이 필요합니다.")
        // 증명서 가입 여부
        if (companyRepository.existsByCertificate(payload.certificate)) throw Exception("이미 가입한 증명서입니다.")
        // 조직명 가입 여부
        if (companyRepository.existsByName(payload.name)) throw Exception("이미 가입한 조직명입니다.")
        companyRepository.save(Company.create(payload))
    }
    /**
     * 기업 정보 생성
     */
    suspend fun create2(payload: CompanyPayload): Company {
        // 기업 이메일, 휴대폰 번호 인증 필요 !! , 현재는 mock 처리
        if (!isVerified(payload.email, payload.phoneNumber)) throw Exception("인증이 필요합니다.")
        // 증명서 가입 여부
        if (companyRepository.existsByCertificate(payload.certificate)) throw Exception("이미 가입한 증명서입니다.")
        return companyRepository.save(Company.create(payload))
    }

    /**
     * 회사 조회
     * @param id 회사 고유 번호
     */
    suspend fun getById(id: ObjectId): CompanyView? =
        companyRepository.findById(id)?.toCompanyView()

    /**
     * 이메일, 휴대폰 번호 인증 여부, 인증 프로세스 생성후 리팩토링
     */
    private fun isVerified(email: String, phoneNumber: String): Boolean = true
}