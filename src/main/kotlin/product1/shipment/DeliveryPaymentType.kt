package product1.shipment

/**
 * 배송비 선결제 설정 (무료 배송비 제외)
 */
enum class DeliveryPaymentType {

    /**
     * 착불
     */
    COD,

    /**
     * 선결제
     */
    PREPAYMENT
}