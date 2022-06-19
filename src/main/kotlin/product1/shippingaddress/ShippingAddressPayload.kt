package product1.shippingaddress

import product1.validation.ValidationConstants.NAME_REGEX
import product1.validation.ValidationConstants.PHONE_NUMBER_REGEX
import product1.validation.ValidationConstants.SHIPPING_ADDRESS_NAME_REGEX
import product1.validation.ValidationConstants.ZIP_CODE_REGEX
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 * 배송지 입력 필드
 */
data class ShippingAddressPayload(

    /**
     * 배송지명
     */
    @field:Pattern(regexp = SHIPPING_ADDRESS_NAME_REGEX, message = "올바르지 않은 형식입니다")
    val name: String,

    /**
     * 수령인1
     */
    @field:Pattern(regexp = NAME_REGEX, message = "올바르지 않은 형식입니다")
    val firstRecipient: String,

    /**
     * 수령인2
     */
    @field:Pattern(regexp = NAME_REGEX, message = "올바르지 않은 형식입니다")
    val secondRecipient: String? = null,

    /**
     * 전화번호1
     */
    @field:Pattern(regexp = PHONE_NUMBER_REGEX, message = "올바르지 않은 형식입니다")
    val firstPhoneNumber: String,

    /**
     * 전화번호2
     */
    @field:Pattern(regexp = PHONE_NUMBER_REGEX, message = "올바르지 않은 형식입니다")
    val secondPhoneNumber: String? = null,

    /**
     * 우편번호
     */
    @field:Pattern(regexp = ZIP_CODE_REGEX, message = "올바르지 않은 형식입니다")
    val zipCode: String,

    /**
     * 기본 주소
     */
    @field:NotBlank(message = "올바르지 않은 형식입니다")
    val line1: String,

    /**
     * 상세 주소
     */
    val line2: String? = null,
)