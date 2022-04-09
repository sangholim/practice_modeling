package product1.company

/**
 * 기업 배송지
 * 기업 직원이 상품 구매시 받을 배송지
 * 기업 내에서 관리
 */
data class CompanyShippingAddress(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 기업 번호
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