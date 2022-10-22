package product1.cart.cartFacadeService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
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
import product1.fixture.itemPayload
import product1.fixture.lineItemPayload
import product1.product.ProductService
import product1.product.fixture.ProductFixture
import product1.variant.VariantFixture
import product1.variant.VariantService

class AddLineItemTest : BehaviorSpec({
    val cartService: CartService = mockk()
    val productService: ProductService = mockk()
    val variantService: VariantService = mockk()
    val cartFacadeService = CartFacadeService(cartService, productService, variantService)

    Given("구매 항목 담기") {
        val employeeId = ObjectId.get()
        val productId = ObjectId.get()
        val product = ProductFixture.createProduct().copy(id = productId)
        val variants = VariantFixture.createVariants(product).map { it.copy(id = ObjectId.get()) }
        val emptyCart = Cart.of(employeeId)

        When("장바구니가 존재하지 않는 경우") {
            clearAllMocks()
            coEvery { cartService.getCart(employeeId) } returns null

            val result = shouldThrow<ResponseStatusException> {
                cartFacadeService.addLineItem(employeeId, lineItemPayload())
            }

            Then("status: NOT_FOUND") {
                result.status shouldBe HttpStatus.NOT_FOUND
            }
        }

        When("상품이 존재하지 않는 경우") {
            clearAllMocks()
            coEvery { cartService.getCart(employeeId) } returns emptyCart
            coEvery { productService.getProduct(productId) } throws ResponseStatusException(HttpStatus.NOT_FOUND)
            val result = shouldThrow<ResponseStatusException> {
                cartFacadeService.addLineItem(employeeId, lineItemPayload { this.productId = productId })
            }

            Then("status: NOT_FOUND") {
                result.status shouldBe HttpStatus.NOT_FOUND
            }
        }

        When("옵션 상품이 없는 경우") {
            clearAllMocks()
            val payload = lineItemPayload {
                this.productId = productId
                this.items = List(5) {
                    itemPayload()
                }
            }
            coEvery { cartService.getCart(employeeId) } returns emptyCart
            coEvery { productService.getProduct(productId) } returns product
            payload.items.forEach {
                coEvery { variantService.getById(it.variantId) } returns null
            }
            val result = shouldThrow<ResponseStatusException> {
                cartFacadeService.addLineItem(employeeId, payload)
            }

            Then("status: NOT_FOUND") {
                result.status shouldBe HttpStatus.NOT_FOUND
            }
        }

        When("새로운 구매항목을 담는 경우") {
            clearAllMocks()
            val payload = lineItemPayload {
                this.productId = productId
                this.items = variants.subList(0, 2).map { variant ->
                    itemPayload {
                        this.variantId = variant.id!!
                        this.count = 1
                    }
                }
            }
            val items = payload.items.map { itemPayload ->
                val variant = variants.first { it.id == itemPayload.variantId }
                Item.of(variant, itemPayload.count)
            }
            val lineItem = LineItem.of(product, items)
            val updateCart = emptyCart.addLineItem(lineItem).updateSummary()

            coEvery { cartService.getCart(employeeId) } returns emptyCart
            coEvery { productService.getProduct(productId) } returns product
            payload.items.forEach { item ->
                coEvery {
                    variantService.getById(item.variantId)
                } returns variants.first { it.id!! == item.variantId }
            }
            coEvery { cartService.updateCart(updateCart) } returns updateCart
            val result = cartFacadeService.addLineItem(employeeId = employeeId, payload = payload)

            Then("cart LineItem 0보다 크다") {
                result.lineItems.size shouldBeGreaterThan 0
            }

            Then("cart.summary.total 0보다 크다") {
                result.summary.total shouldBeGreaterThan 0
            }
        }

        When("기존 구매항목에서 수량을 추가한다") {
            clearAllMocks()
            val originItems = variants.subList(0, 2).map { variant ->
                Item.of(variant, 1)
            }
            val originLineItem = LineItem.of(product, originItems)
            val cart = emptyCart.addLineItem(originLineItem).updateSummary()

            val payload = lineItemPayload {
                this.productId = productId
                this.items = originItems.map { item ->
                    itemPayload {
                        this.variantId = item.variantId
                        this.count = 1
                    }
                }
            }
            val items = payload.items.map { itemPayload ->
                val variant = variants.first { it.id == itemPayload.variantId }
                Item.of(variant, itemPayload.count)
            }
            val lineItem = cart.findLineItem(product.id!!)!!.addItems(items)
            val updateCart = cart.addLineItem(lineItem).updateSummary()

            coEvery { cartService.getCart(employeeId) } returns cart
            coEvery { productService.getProduct(productId) } returns product
            payload.items.forEach { item ->
                coEvery {
                    variantService.getById(item.variantId)
                } returns variants.first { it.id!! == item.variantId }
            }
            coEvery { cartService.updateCart(updateCart) } returns updateCart
            val result = cartFacadeService.addLineItem(employeeId = employeeId, payload = payload)

            Then("cart LineItem 내의 항목별 수량은 2이다.") {
                result.lineItems.flatMap { it.items }
                    .count { it.count == 2 } shouldBe result.lineItems.flatMap { it.items }.count()
            }

            Then("result.summary.subtotal = cart.summary.subtotal * 2") {
                result.summary.subtotal shouldBe cart.summary.subtotal.times(2)
            }
        }
    }
})