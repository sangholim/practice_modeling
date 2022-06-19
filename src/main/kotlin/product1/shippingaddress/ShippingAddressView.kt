package product1.shippingaddress

import org.bson.types.ObjectId
import java.time.Instant

data class ShippingAddressView(
    /**
     * 고유 번호
     */
    val id: ObjectId,

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
    val secondRecipient: String?,

    /**
     * 전화번호1
     */
    val firstPhoneNumber: String,

    /**
     * 전화번호2
     */
    val secondPhoneNumber: String?,

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
    val createAt: Instant,
)

fun ShippingAddress.toShippingAddressView() = ShippingAddressView(
    id = id!!,
    name = name,
    firstRecipient = firstRecipient,
    secondRecipient = secondRecipient,
    firstPhoneNumber = firstPhoneNumber,
    secondPhoneNumber = secondPhoneNumber,
    zipCode = zipCode,
    line1 = line1,
    line2 = line2,
    createAt = createAt!!
)