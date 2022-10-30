package product1.cart.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.server.ResponseStatusException
import product1.cart.CartController
import product1.cart.CartFacadeService
import product1.cart.domain.Cart

@WebFluxTest(controllers = [CartController::class])
class DeleteLineItemTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val cartFacadeService: CartFacadeService
) : BehaviorSpec({

    beforeEach {
        clearAllMocks()
    }

    Given("구매 항목 제거 API 요청") {
        val employeeId = ObjectId.get()
        val productId = ObjectId.get()
        val invalidId = ObjectId.get()

        val cart = Cart.of(employeeId)

        When("직원 장바구니가 없는 경우") {
            coEvery {
                cartFacadeService.deleteLineItem(invalidId, productId)
            } throws ResponseStatusException(HttpStatus.NOT_FOUND)

            val exchange = webTestClient.delete()
                .uri("/carts/$invalidId/lineItems/$productId")
                .exchange()

            Then("status: 404 Not Found") {
                exchange.expectStatus().isNotFound
            }
        }

        When("직원 장바구니에 구매항목이 없는 경우") {
            coEvery {
                cartFacadeService.deleteLineItem(employeeId, invalidId)
            } throws ResponseStatusException(HttpStatus.NOT_FOUND)

            val exchange = webTestClient.delete()
                .uri("/carts/$employeeId/lineItems/$invalidId")
                .exchange()

            Then("status: 404 Not Found") {
                exchange.expectStatus().isNotFound
            }
        }

        When("구매항목 제거 성공한 경우") {
            coEvery {
                cartFacadeService.deleteLineItem(employeeId, productId)
            } returns cart

            val exchange = webTestClient.delete()
                .uri("/carts/$employeeId/lineItems/$productId")
                .exchange()

            Then("status: 204 No Content") {
                exchange.expectStatus().isNoContent
            }

            Then("body: empty") {
                exchange.expectBody().isEmpty
            }
        }
    }
})