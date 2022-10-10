package product1.fixture

import product1.company.CompanyPayload

inline fun companyPayload(block: CompanyPayloadBuilder.() -> Unit = {}) =
    CompanyPayloadBuilder().apply(block).build()
class CompanyPayloadBuilder {

    var name: String = ""
    var email: String = ""
    var phoneNumber: String = ""
    var certificate: String = ""
    var address: String = ""

    fun build() = CompanyPayload(
        name, email, phoneNumber, certificate, address
    )
}
