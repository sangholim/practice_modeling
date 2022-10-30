package product1.cart.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.server.ResponseStatusException
import product1.cart.CartController
import product1.cart.CartFacadeService
import product1.cart.domain.Cart
import product1.cart.dto.LineItemPayload
import product1.fixture.itemPayload
import product1.fixture.lineItemPayload

@WebFluxTest(controllers = [CartController::class])
class AddLineItemTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val cartFacadeService: CartFacadeService
) : BehaviorSpec({

    beforeEach {
        clearAllMocks()
    }

    fun exchange(employeeId: ObjectId, payload: LineItemPayload) =
        webTestClient.post()
            .uri("/carts/$employeeId/lineItems")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .exchange()

    Given("장바구니 담기 API 요청") {
        val employeeId = ObjectId.get()
        val productId = ObjectId.get()
        val cart = Cart.of(employeeId)
        val payload = lineItemPayload {
            this.productId = productId
            this.items = List(5) {
                itemPayload()
            }
        }

        When("직원 장바구니가 없는 경우") {

            coEvery {
                cartFacadeService.addLineItem(employeeId, any())
            } throws ResponseStatusException(HttpStatus.NOT_FOUND)

            val exchange = exchange(employeeId, payload)

            Then("status: 404 Not Found") {
                exchange.expectStatus().isNotFound
            }
        }

        When("직원 장바구니에 구매항목이 없는 경우") {
            coEvery {
                cartFacadeService.addLineItem(employeeId, any())
            } throws ResponseStatusException(HttpStatus.NOT_FOUND)

            val exchange = exchange(employeeId, payload)

            Then("status: 404 Not Found") {
                exchange.expectStatus().isNotFound
            }
        }

        When("구매항목 담기 성공한 경우") {
            coEvery {
                cartFacadeService.addLineItem(employeeId, any())
            } returns cart

            val exchange = exchange(employeeId, payload)

            Then("status: 201 Created") {
                exchange.expectStatus().isCreated
            }

            Then("body: cart") {
                exchange.expectBody<Cart>()
            }
        }

    }
})