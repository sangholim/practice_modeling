package product1.employee

import product1.validation.ValidationConstants.EMAIL_REGEX
import product1.validation.ValidationConstants.NAME_REGEX
import product1.validation.ValidationConstants.PHONE_NUMBER_REGEX
import product1.validation.ValidationConstants.PASSWORD_REGEX
import javax.validation.constraints.Pattern

/**
 * 사원 가입 필드
 */
data class EmployeePayload (
    /**
     * 사원명
     */
    @field:Pattern(regexp = NAME_REGEX, message = "올바르지 않은 형식입니다")
    val name: String,

    /**
     * 직책
     */
    @field:Pattern(regexp = NAME_REGEX, message = "올바르지 않은 형식입니다")
    val position: String,

    /**
     * 이메일
     */
    @field:Pattern(regexp = EMAIL_REGEX, message = "올바르지 않은 형식입니다")
    val email: String,

    /**
     * 휴대폰 번호
     */
    @field:Pattern(regexp = PHONE_NUMBER_REGEX, message = "올바르지 않은 형식입니다")
    val phoneNumber: String,

    /**
     * 비밀번호
     */
    @field:Pattern(regexp = PASSWORD_REGEX, message = "올바르지 않은 형식입니다")
    val password: String,
)