package product1.company

/**
 * 잔고 변경 이력
 */
data class BalanceEvent(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 조직 번호
     */
    val companyId: String,

    /**
     * 조직 총 잔고
     */
    val totalBalance: Int,

    /**
     * 사용/충전 금액
     */
    val credit: Int,

    /**
     * 이력 내용
     */
    val content: String,
)