package product1.employee

/**
 * B2B 사원 (sub-account)
 */
data class Employee(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 회사 번호
     */
    val companyId: String,

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
     * 이메일 인증 여부
     */
    val emailVerified: Boolean,

    /**
     * 휴대폰 번호
     */
    val phoneNumber: String,

    /**
     * 휴대폰 번호 인증 여부
     */
    val phoneNumberVerified: Boolean,

    /**
     * 비밀번호
     */
    val password: String,

    /**
     * 최대 구매량
     * Company 에서 변경 가능
     */
    val purchaseLimit: Int,

    /**
     * 조직 서비스 접근 권한
     */
    val permissions: List<EmployeePermission>,

    /**
     * 시스템 접근 권한
     */
    val roles: List<String>
)
