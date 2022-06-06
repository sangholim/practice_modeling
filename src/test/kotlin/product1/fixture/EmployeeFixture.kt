package product1.fixture

import org.bson.types.ObjectId
import product1.employee.EmployeePayload

object EmployeeFixture {

    val ID: ObjectId = ObjectId("507f191e810c19729de860ea")

    val NAME: String = "홍길동사원"

    val POSITION: String = "경리"

    val EMAIL: String = "test@test.com"

    val PHONE_NUMBER: String = "11122223333"

    val PASSWORD: String = "test1324!"

    fun createEmployeePayload() = EmployeePayload(
        name = NAME,
        position = POSITION,
        email = EMAIL,
        phoneNumber = PHONE_NUMBER,
        password = PASSWORD
    )


    fun createInvalidEmployeePayload() = EmployeePayload(
        name = "0",
        position = "0",
        email = "a",
        phoneNumber = "a",
        password = "aaaaaaaaaaaaaa"
    )
}