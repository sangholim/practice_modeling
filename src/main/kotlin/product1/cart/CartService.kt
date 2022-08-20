package product1.cart

import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import product1.cart.domain.Cart
import product1.cart.domain.CartSummary
import product1.cart.domain.LineItem

@Service
class CartService(
    private val cartRepository: CartRepository
) {

    /**
     * 직원 번호로 장바구니 조회
     * @param employeeId 직원 번호
     */
    suspend fun getCart(employeeId: ObjectId): Cart? =
        cartRepository.findFirstByEmployeeId(employeeId)

    /**
     * 직원 장바구니 저장
     * @param employeeId 직원 번호
     */
    suspend fun createCart(employeeId: ObjectId): Cart =
        cartRepository.save(Cart.of(employeeId))


}