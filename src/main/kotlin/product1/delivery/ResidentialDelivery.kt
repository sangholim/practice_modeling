package product1.delivery

/**
 * 지역 배송 설정
 * 지역 배송 기준 - 우편번호 범위
 * 판매자(기업) 에서 관리
 */
data class ResidentialDelivery(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 배송 업체 고유 번호
     */
    val shipmentProviderId: String,

    /**
     * 지역 배송 이름
     */
    val name: String,

    /**
     * 지역 배송 지원 우편 번호 시작
     */
    val zipCodeFrom: Int,

    /**
     * 지역 배송 지원 우편 번호 종료
     */
    val zipCodeTo: Int,

    /**
     * 지역 배송비
     */
    val price: Int

)