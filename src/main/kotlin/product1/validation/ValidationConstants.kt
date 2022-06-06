package product1.validation

object ValidationConstants {
    private const val ENGLISH = "a-zA-Z"
    private const val KOREAN = "가-힣"
    private const val NUMBER = "\\d"
    private const val SPECIAL_CHARACTER = "!@#$%^&\\*\\(\\)-_\\+\\="
    const val NAME_REGEX = "^[$ENGLISH$KOREAN]{1,10}\$"
    const val EMAIL_REGEX = "^[$ENGLISH$NUMBER]{1,}@[$ENGLISH$NUMBER]{1,}.[$ENGLISH$NUMBER]{1,}\$"
    const val PHONE_NUMBER_REGEX = "^$NUMBER{7,12}\$"
    const val PASSWORD_REGEX = "^(?=.*$NUMBER)(?=.*[$ENGLISH])(?=.*[$SPECIAL_CHARACTER]).{8,20}\$"
}