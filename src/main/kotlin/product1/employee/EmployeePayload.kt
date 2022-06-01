package product1.employee

/**
 * 사원 가입 필드
 */
data class EmployeePayload (
    /**
     * 사원명
     */
    val name: String,

    /**
     * 직책
     */
    val position: String,

    /**
     * 이메일
     */
    val email: String,

    /**
     * 휴대폰 번호
     */
    val phoneNumber: String,

    /**
     * 비밀번호
     */
    val password: String,
)