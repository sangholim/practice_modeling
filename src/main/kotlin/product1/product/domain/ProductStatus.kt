package product1.product.domain

/**
 * 상품 상태
 */
enum class ProductStatus {

    /**
     * 상품 등록시 대기
     * wms 에서 준비하기전
     */
    READY,

    /**
     * 판매
     * wms 에서 재고 준비 된 경우
     */
    SOLD,

    /**
     * 매진
     * 판매중인 상품이 wms에서 재고가 없는 경우
     */
    SOLD_OUT
}