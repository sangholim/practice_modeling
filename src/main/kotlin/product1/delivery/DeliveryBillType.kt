package product1.delivery

/**
 * 배송료 청구 기준 (무료 배송비 제외)
 */
enum class DeliveryBillType {
    /**
     * 정상 판매 가격 기준 (할인전)
     */
    BASE,

    /**
     * 최종 결제 금액 기준
     */
    PURCHASE
}