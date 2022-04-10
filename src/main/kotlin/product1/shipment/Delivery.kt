package product1.shipment

/**
 * 배송비 설정
 */
data class Delivery(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 기업 번호
     */
    val companyId: String,

    /**
     * 배송방법: 택배 ...
     */
    val type: String,

    /**
     * 배송 설정: 국내, TODO: 해외, 국내/해외
     */
    val regionType: DeliveryRegionType,

    /**
     * 배송지역
     */
    val region: String,

    /**
     * 최소 배송기간
     */
    val minDay: Int,

    /**
     * 최대 배송기간
     */
    val maxDay: Int,

    /**
     * 배송비 설정 타입
     */
    val priceType: DeliveryPriceType,

    /**
     * 배송 상세 설정
     */
    val detail : DeliveryDetail?
)