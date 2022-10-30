package product1.cart.cartFacadeService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import product1.cart.CartFacadeService
import product1.cart.CartService
import product1.cart.domain.Cart
import product1.cart.domain.Item
import product1.cart.domain.LineItem
import product1.product.ProductService
import product1.product.fixture.ProductFixture
import product1.variant.VariantFixture
import product1.variant.VariantService

class DeleteLineItemTest : BehaviorSpec({
    val cartService: CartService = mockk()
    val productService: ProductService = mockk()
    val variantService: VariantService = mockk()
    val cartFacadeService = CartFacadeService(cartService, productService, variantService)

    beforeEach {
        clearAllMocks()
    }

    Given("구매 항목 제거") {
        val employeeId = ObjectId.get()
        val productId = ObjectId.get()
        val product = ProductFixture.createProduct().copy(id = productId)
        val variants = VariantFixture.createVariants(product).map { it.copy(id = ObjectId.get()) }
        val items = variants.subList(0, 2).map { variant ->
            Item.of(variant, 1)
        }
        val lineItem = LineItem.of(product, items)
        val cart = Cart.of(employeeId).addLineItem(lineItem).updateSummary()

        When("직원의 장바구니가 없는 경우") {
            val expected = ResponseStatusException(HttpStatus.NOT_FOUND)

            coEvery { cartService.getCart(employeeId) } returns null

            val result = shouldThrow<ResponseStatusException> {
                cartFacadeService.deleteLineItem(employeeId, productId)
            }

            Then("ResponseStatusException: NOT FOUND") {
                result shouldBe expected
            }
        }

        When("직원의 장바구니에 상품이 없는 경우") {
            val invalidProductId = ObjectId.get()
            val expected = ResponseStatusException(HttpStatus.NOT_FOUND)

            coEvery { cartService.getCart(employeeId) } returns cart

            val result = shouldThrow<ResponseStatusException> {
                cartFacadeService.deleteLineItem(employeeId, invalidProductId)
            }

            Then("ResponseStatusException: NOT FOUND") {
                result shouldBe expected
            }
        }

        When("구매 항목 제거 정상 처리한 경우") {
            val expected = cart.deleteLineItem(productId)!!.updateSummary()

            coEvery { cartService.getCart(employeeId) } returns cart
            coEvery { cartService.updateCart(expected) } returns expected

            val result = cartFacadeService.deleteLineItem(employeeId, productId)

            Then("제거한 lineItem 은 존재하지 않는다") {
                result.findLineItem(productId) shouldBe null
            }

            Then("장바구니 결제 정보 subTotal 은 0 이다") {
                result.summary.subtotal shouldBe 0
            }
        }
    }
})