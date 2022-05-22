package product1.validation

object ValidationConstants {
    private const val ENGLISH_KOREAN = "[a-zA-Z가-힣]"
    private const val ENGLISH_NUMBER = "[a-zA-Z0-9]"
    const val COMPANY_NAME = "^$ENGLISH_KOREAN{1,10}\$"
    const val COMPANY_EMAIL = "^$ENGLISH_NUMBER{1,}@$ENGLISH_NUMBER{1,}.$ENGLISH_NUMBER{1,}\$"
    const val COMPANY_PHONE_NUMBER = "^\\d{7,12}\$"
}