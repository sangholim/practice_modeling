package product1.variant.variantService

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import product1.product.fixture.ProductFixture
import product1.variant.VariantFixture
import product1.variant.VariantRepository
import product1.variant.VariantService

class GetVariantByCodeTest : BehaviorSpec({
    val variantRepository: VariantRepository = mockk()
    val variantService = VariantService(variantRepository)

    beforeTest {
        clearAllMocks()
    }

    Given("옵션 상품이 없는 경우") {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product)
        Then("throw ResponseStatusException(HttpStatus.NOT_FOUND)") {
            coEvery { variantRepository.findByProductId(product.id!!) } returns variants.asFlow()
            val result = shouldThrow<ResponseStatusException> { variantService.getVariantByCode(product.id!!, "") }
            result.status shouldBe HttpStatus.NOT_FOUND
        }
    }

    Given("옵션 상품이 있는 경우") {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product)
        val expected = variants.first()
        Then("Variant 타입의 객체를 반환한다.") {
            coEvery { variantRepository.findByProductId(product.id!!) } returns variants.asFlow()
            val result = variantService.getVariantByCode(product.id!!, expected.code)
            result shouldBe expected
        }
    }
})