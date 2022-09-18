package product1.variant.variantService

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.bson.types.ObjectId
import product1.product.fixture.ProductFixture
import product1.variant.VariantFixture
import product1.variant.VariantRepository
import product1.variant.VariantService

class GetVariantByIdAndProductIdTest : BehaviorSpec({
    val variantRepository: VariantRepository = mockk()
    val variantService = VariantService(variantRepository)

    beforeTest {
        clearAllMocks()
    }

    Given("옵션 상품 번호와 상품 번호로 조회시 존재하는 경우") {
        val product = ProductFixture.createProduct()
        val variants = VariantFixture.createVariants(product).map { it.copy(id = ObjectId.get()) }
        val expected = variants.first()
        Then("Variant 타입의 객체를 반환한다.") {
            coEvery { variantRepository.findByIdAndProductId(expected.id!!, expected.productId) } returns expected
            val result = variantService.getVariantByIdAndProductId(expected.id!!, expected.productId)
            result shouldBe expected
        }
    }

})