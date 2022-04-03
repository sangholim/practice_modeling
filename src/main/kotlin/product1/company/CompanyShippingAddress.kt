package product1.company

/**
 * 조직 배송지
 */
data class CompanyShippingAddress(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 조직 번호
     */
    val companyId: String,

    /**
     * 배송지명
     */
    val name: String,

    /**
     * 수령인1
     */
    val receiver1: String,

    /**
     * 수령인2
     */
    val receiver2: String,

    /**
     * 휴대폰 번호1
     */
    val phoneNumber1: String,

    /**
     * 휴대폰 번호2
     */
    val phoneNumber2: String,

    /**
     * 우편 번호
     */
    val zipCode: String,

    /**
     * 기본 주소
     */
    val line1: String,

    /**
     * 상세 주소
     */
    val line2: String
)