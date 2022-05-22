package product1.company

import org.bson.types.ObjectId

/**
 * 회원 조회 데이터
 */
data class CompanyView(

    /**
     * 고유 번호
     */
    val id: ObjectId,

    /**
     * 조직명
     */
    val name: String,

    /**
     * 조직 이메일
     */
    val email: String,

    /**
     * 휴대폰 번호
     */
    val phoneNumber: String,

    /**
     * 인증서
     */
    val certificate: String,

    /**
     * 조직의 주소
     */
    val address: String,

    /**
     * 잔고
     */
    val balance: Int,
)

fun Company.toCompanyView() = CompanyView(
    id = this.id!!,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    certificate = certificate,
    address = address,
    balance = balance
)