package product1.company

import java.time.Instant

/**
 * 조직 결제 관리 클래스
 * 결제 서비스 제공 회사의 승인을 받은 상태여야 한다.
 * 권한이 있는경우 사원도 생성/수정/제거 가능
 */
data class CompanyPayment(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 조직 번호
     */
    val companyId: String,

    /**
     * 결제 서비스 제공사 번호
     */
    val paymentProviderId: String,

    /**
     * 결제 수단
     */
    val name: String,

    /**
     * 결제 수단 고유 번호
     */
    val identifier: String,

    /**
     * 결제 수단 비밀번호
     */
    val password: String,

    /**
     * 결제 수단 만료일
     */
    val expiredAt: Instant,

    /**
     * 결제 수단 인증 여부
     */
    val verified: Boolean,

    /**
     * 청구서 주서
     */
    val billingAddress: String
)