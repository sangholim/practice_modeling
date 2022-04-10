package product1.delivery

/**
 * 배송비 타입
 */
enum class DeliveryPriceType {

    /**
     * 무료 배송비
     */
    FREE,

    /**
     * 고정 배송비
     */
    FLAT,

    /**
     * 구매 금액에 따른 부과
     */
    AMOUNT,

    /**
     * 구매 금액별 차등 배송
     */
    DIFFERENTIAL_AMOUNT,

    /**
     * 상품 무게별 차등 배송
     */
    DIFFERENTIAL_WEIGHT,

    /**
     * 상품 수량별 차등 배송
     */
    DIFFERENTIAL_COUNT,

    /**
     * 상품 수량에 비례하여 배송비 부과
     */
    PROPORTIONAL_COUNT
}