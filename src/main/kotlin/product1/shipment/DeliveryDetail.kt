package product1.shipment

/**
 * 배송 상세 설정
 */
data class DeliveryDetail(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 상새 배송비 (무료 배송비 제외)
     */
    val prices: List<DeliveryPriceDetail>,

    /**
     * 배송료 청구 기준
     */
    val billType: DeliveryBillType,

    /**
     * 배송비 선결제 설정
     */
    val paymentType: DeliveryPaymentType
)