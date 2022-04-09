package product1.shipment

/**
 * 배송 서비스 제공 업체
 * 마켓 관리자가 관리
 */
data class ShipmentProvider (

    /**
     * 고유 번호
     */
    val id:String,

    /**
     * 배송지 업체명
     */
    val name: String,

    /**
     * 배송지 업체 이메일
     */
    val email:String,

    /**
     * 배송지 업체 전화 번호
     */
    val phoneNumber:String,

    /**
     * 배송지 업체 홈페이지
     */
    val homepage:String,

    /**
     * 기본 배송비
     */
    val originalPrice: Int,

)