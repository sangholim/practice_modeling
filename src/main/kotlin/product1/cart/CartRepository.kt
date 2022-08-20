package product1.cart

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import product1.cart.domain.Cart

interface CartRepository : CoroutineCrudRepository<Cart, ObjectId> {

    suspend fun findFirstByEmployeeId(employeeId: ObjectId): Cart?
}