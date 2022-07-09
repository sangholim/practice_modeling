package product1.product

object ProductOptionCode {

    private val CHARSET = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    fun generateCode() = List(6) {
        CHARSET.random()
    }.joinToString("")

}