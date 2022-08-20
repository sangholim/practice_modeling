package product1.employee

import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import product1.cart.CartService

@Service
class EmployeeFacadeService(
    private val employeeService: EmployeeService,
    private val cartService: CartService
) {

    /**
     * 직원 생성
     * @param companyId 회사 번호
     * @param payload 직원 생성 필드
     */
    suspend fun createEmployee(companyId: ObjectId, payload: EmployeePayload): Employee {
        val employee = employeeService.create(companyId, payload)
        cartService.createCart(employee.id!!)
        return employee
    }
}