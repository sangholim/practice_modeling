package product1.payment

import java.time.Instant

/**
 * 외부 결제사 관리
 */
data class PaymentProvider(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 조직 번호
     */
    val companyId: String,

    /**
     * 서비스명
     */
    val name: String,

    /**
     * 결제사명
     */
    val providerName: String,

    /**
     * 평가일
     */
    val issuedAt: Instant,

    /**
     * 처리 상태
     */
    val status: PaymentProviderStatus,

    /**
     * 상세 정보
     */
    val detailInfo: String
)