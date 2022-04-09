package product1.payment

import java.time.Instant

/**
 * 주문 결제 서비스 정보
 * 권한에 따라 사원도 생성/수정/제거 가능
 */
data class Payment(

    /**
     * 고유 번호
     */
    val id: String,

    /**
     * 조직 번호
     */
    val companyId: String,

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
     * 청구서 주소
     */
    val billingAddress: String
)