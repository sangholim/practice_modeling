package product1.company

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.Instant

/**
 * B2B 조직 - (account 역할)
 */
@Document
data class Company(

    /**
     * 고유 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 조직명
     */
    val name: String,

    /**
     * 조직 이메일
     */
    val email: String,

    /**
     * 이메일 인증 여부
     */
    val emailVerified: Boolean,

    /**
     * 휴대폰 번호
     */
    val phoneNumber: String,

    /**
     * 휴대폰 번호 인증 여부
     */
    val phoneNumberVerified: Boolean,

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

    /**
     * 생성일
     */
    @CreatedDate
    val createAt: Instant? = null,

    /**
     * 수정일
     */
    @LastModifiedDate
    val modifiedAt: Instant? = null
) {
    companion object {

        /**
         * 회사 정보 생성
         */
        fun create(payload: CompanyPayload): Company =
            Company(
                name = payload.name,
                email = payload.email,
                emailVerified = true,
                phoneNumber = payload.phoneNumber,
                phoneNumberVerified = true,
                certificate = payload.certificate,
                address = payload.address,
                balance = 0
            )
    }
}