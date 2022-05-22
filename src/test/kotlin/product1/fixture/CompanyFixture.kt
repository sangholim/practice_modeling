package product1.fixture

import org.bson.types.ObjectId
import product1.company.Company
import product1.company.CompanyPayload

object CompanyFixture {

    const val name = "테스트 회사"
    const val email = "test@test.com"
    const val phoneNumber = "1112223333"
    const val certificate = "testCertificate"
    const val address = "테스트 동 테스트시"

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