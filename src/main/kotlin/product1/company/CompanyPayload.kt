package product1.company

import product1.validation.ValidationConstants.EMAIL_REGEX
import product1.validation.ValidationConstants.NAME_REGEX
import product1.validation.ValidationConstants.PHONE_NUMBER_REGEX
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 * 회사 필드 데이터
 */
data class CompanyPayload (

    /**
     * 조직명
     */
    @field:Pattern(regexp = NAME_REGEX, message = "올바르지 않은 형식입니다")
    val name: String,

    /**
     * 조직 이메일
     */
    @field:Pattern(regexp = EMAIL_REGEX, message = "올바르지 않은 형식입니다")
    val email: String,

    /**
     * 휴대폰 번호
     */
    @field:Pattern(regexp = PHONE_NUMBER_REGEX, message = "올바르지 않은 형식입니다")
    val phoneNumber: String,

    /**
     * 인증서
     */
    @field:NotBlank(message = "올바르지 않은 형십입니다")
    val certificate: String,

    /**
     * 조직의 주소
     */
    @field:NotBlank(message = "올바르지 않은 형십입니다")
    val address: String,
): java.io.Serializable