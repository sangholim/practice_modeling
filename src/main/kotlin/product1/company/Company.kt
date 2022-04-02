package product1.company

/**
 * B2B 조직 - (account 역할)
 */
data class Company (

    /**
     * 고유 번호
     */
    val id:String,

    /**
     * 조직 이메일
     */
    val email :String,

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
     * 조직의 주소
     */
    val address: String

    // TODO Balance 잔고
)