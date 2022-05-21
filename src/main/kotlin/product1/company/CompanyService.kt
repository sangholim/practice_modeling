package product1.company

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
        if(!isVerified(payload.email, payload.phoneNumber)) throw Exception("인증이 필요합니다.")
        // 인증서는 외부 integration이 있는경우 검증 로직 생성
        companyRepository.save(Company.create(payload))
    }

    /**
     * 이메일, 휴대폰 번호 인증 여부
     */
    private fun isVerified(email:String, phoneNumber:String):Boolean = true
}