package product1.employee

import org.bson.types.ObjectId
import product1.config.Constants

object EmployeeFixture {

    fun create():Employee = Employee(
        companyId = ObjectId.get(),
        name = "홍길동",
        position = "경리",
        email = "giltong@gil.com",
        emailVerified = true,
        phoneNumber = "0000000000",
        phoneNumberVerified = true,
        password = "test1234!",
        permissions = listOf(EmployeePermission.PAYMENT, EmployeePermission.SHIPPING_ADDRESS),
        roles = listOf(Constants.USER)
    )
}