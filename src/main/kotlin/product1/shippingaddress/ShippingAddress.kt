package product1.shippingaddress

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * 기업 배송지
 */
@Document
data class ShippingAddress(
    /**
     * 고유 번호
     */
    val id: ObjectId? = null,

    /**
     * 기업 번호
     */
    val companyId: ObjectId,

    /**
     * 배송지명
     */
    val name: String,

    /**
     * 수령인1
     */
    val firstRecipient: String,

    /**
     * 수령인2
     */
    val secondRecipient: String? = null,

    /**
     * 전화번호1
     */
    val firstPhoneNumber: String,

    /**
     * 전화번호2
     */
    val secondPhoneNUmber: String? = null,

    /**
     * 우편번호
     */
    val zipCode: String,

    /**
     * 기본 주소
     */
    val line1: String,

    /**
     * 상세 주소
     */
    val line2: String? = null,

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

    /**
     * 배송지 수정
     * @param payload 수정 필드
     */
    fun update(payload: ShippingAddressPayload) = copy(
        name = payload.name,
        firstRecipient = payload.firstRecipient,
        secondRecipient = payload.secondRecipient,
        firstPhoneNumber = payload.firstPhoneNumber,
        secondPhoneNUmber = payload.secondPhoneNumber,
        zipCode = payload.zipCode,
        line1 = payload.line1,
        line2 = payload.line2
    )

    companion object {

        /**
         * 배송지 생성
         * @param companyId 회사 번호
         * @param payload 생성 필드
         */
        fun create(companyId: ObjectId, payload: ShippingAddressPayload) =
            ShippingAddress(
                companyId = companyId,
                name = payload.name,
                firstRecipient = payload.firstRecipient,
                secondRecipient = payload.secondRecipient,
                firstPhoneNumber = payload.firstPhoneNumber,
                secondPhoneNUmber = payload.secondPhoneNumber,
                zipCode = payload.zipCode,
                line1 = payload.line1,
                line2 = payload.line2
            )
    }
}