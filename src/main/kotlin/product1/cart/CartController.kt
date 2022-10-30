package product1.cart

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import product1.cart.domain.Cart
import product1.cart.dto.LineItemPayload
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/carts"])
class CartController(
    private val cartFacadeService: CartFacadeService
) {

    /**
     * 장바구니 상품 담기
     * @param employeeId 직원번호
     * @param payload 구매 항목 필드 데이터
     */
    @PostMapping(value = ["/{employeeId}/lineItems"])
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun addLineItem(
        @PathVariable employeeId: ObjectId,
        @Valid @RequestBody payload: LineItemPayload
    ): Cart =
        cartFacadeService.addLineItem(employeeId, payload)

    @DeleteMapping(value = ["/{employeeId}/lineItems/{productId}"])
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun deleteLineItem(
        @PathVariable employeeId: ObjectId,
        @PathVariable productId: ObjectId,
    ) {
        cartFacadeService.deleteLineItem(employeeId, productId)
    }
}