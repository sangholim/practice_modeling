package product1.fixture

import org.bson.types.ObjectId
import product1.company.Company
import product1.company.CompanyPayload
import product1.company.CompanyView

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

object CompanyFixture {

    val id: ObjectId = ObjectId("507f1f77bcf86cd799439011")
    const val name = "테스트회사"
    const val email = "test@test.com"
    const val phoneNumber = "1112223333"
    const val certificate = "testCertificate"
    const val address = "테스트 동 테스트시"

    fun createCompanyView(): CompanyView = CompanyView(
        id = id,
        name = name,
        email = email,
        phoneNumber = phoneNumber,
        certificate = certificate,
        address = address,
        balance = 0
    )

    fun createInvalidCompanyPayload(): CompanyPayload = CompanyPayload(
        name = "ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ",
        email = "가@가.가",
        phoneNumber = "가",
        certificate = "",
        address = ""
    )

    fun createCompanyPayload(): CompanyPayload = CompanyPayload(
        name = name,
        email = email,
        phoneNumber = phoneNumber,
        certificate = certificate,
        address = address
    )

    fun createCompany(id: ObjectId? = null): Company = Company(
        id = id,
        name = name,
        email = email,
        emailVerified = true,
        phoneNumber = phoneNumber,
        phoneNumberVerified = true,
        certificate = certificate,
        address = address,
        balance = 0
    )
}