package product1.fixture

import product1.employee.EmployeePayload

inline fun employeePayload(block: EmployeePayloadBuilder.() -> Unit = {}) =
    EmployeePayloadBuilder().apply(block).build()

class EmployeePayloadBuilder {

    var name: String = ""
    var position: String = ""
    var email: String = ""
    var phoneNumber: String = ""
    var password: String = ""

    fun build() = EmployeePayload(
        name, position, email, phoneNumber, password
    )
}
