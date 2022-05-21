package product1.company

/**
 * 회사 필드 데이터
 */
data class CompanyPayload (

    /**
     * 조직명
     */
    val name: String,

    /**
     * 조직 이메일
     */
    val email: String,

    /**
     * 휴대폰 번호
     */
    val phoneNumber: String,

    /**
     * 인증서
     */
    val certificate: String,

    /**
     * 조직의 주소
     */
    val address: String,
)