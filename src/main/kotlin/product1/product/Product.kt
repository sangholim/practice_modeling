package product1.product

/**
 * 상품
 * 배송비는 배송비 정책에 준수한다.
 */
data class Product(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 판매자(기업) 번호
     */
    val companyId: String,

    /**
     * 상품명
     */
    val name: String,

    /**
     * 상품 상태
     */
    val status: ProductSaleStatus,

    /**
     * 상품 코드
     */
    val code: String,

    /**
     * 세계 상품 코드
     */
    val upc: String,

    /**
     * 기본 판매 가격
     */
    val price: Int,

    /**
     * 상품 상세
     */
    val description: ProductDescription,

    /**
     * 진열 상태 노출
     */
    val isDisplay: Boolean,

    /**
     * 판매 상태
     */
    val saleStatus: ProductSaleStatus,

    /**
     * 상품 옵션 리스트
     */
    val options: List<ProductOption>,

    /**
     * 과세율
     */
    val taxRate: Float
)
