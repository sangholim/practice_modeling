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
     * 배송 설정: 국내, 해외, 국개/해외
     */
    val regionType: String,

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
     * 배송비 설정: `무료 배송비` `고정 배송비` `구매 금액에 따른 부과` `구매 금액별 차등 배송` `상품 무게별 차등 배송료` `상품 수량별 차등 배송` `상품 수량에 비례하여 배송비 부과`
     */
    val priceType: String,

    /**
     * 상새 배송비 (무료 배송비 제외)
     */
    val details: List<DeliveryDetail>?,

    /**
     * 선택 상품 분류 적용/처리 (무료 배송비 제외): `제한없음` ...
     */
    val selectedProductType: String?,

    /**
     * 배송료 청구 기준 (무료 배송비 제외): `할인전 정상 판매 가격 기준` `최종 주문(결제) 금액 기준`
     */
    val billType: String?,

    /**
     * 배송비 선결제 설정 (무료 배송비 제외): `착불` `선결제` `착불/선결제`
     */
    val paymentType: String?
)