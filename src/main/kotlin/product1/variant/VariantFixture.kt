package product1.variant

import org.bson.types.ObjectId
import product1.product.domain.Product
import product1.product.domain.ProductOption
import product1.product.fixture.ProductOptionFixture.createOptionPoints
import java.util.*
import kotlin.random.Random

object VariantFixture {

    private val NAMES = listOf("size", "color", "design")

    private val SIZES = listOf("L", "M", "S")

    private val COLORS = listOf("WHITE", "BLACK", "GREEN")

    private val DESIGNS = listOf("STRIPED", "PLAIN", "DOTTED")

    private val VARIANT_MAP = mapOf(
        "size" to SIZES,
        "color" to COLORS,
        "design" to DESIGNS
    )

    /**
     * 상품 옵션 데이터 리스트 생성
     * @param length 크기
     */
    fun createVariants(length: Int): List<Variant> {
        val variants = mutableListOf<Variant>()
        for (i in 1..length) {
            val variant = createVariant()
            // 동일한 옵션정보들일 가지고 있는 경우, 추가하지 않음
            variants.firstOrNull { it.options == variant.options } ?: variants.add(variant)
        }
        return variants
    }

    /**
     * 상품 도메인 이용하여 상품 상세 정보 데이터생성
     */
    fun createVariants(product: Product): List<Variant> {
        val productOptionPoints = product.createOptionPoints()
        // 상품 옵션 좌표를 베리언트 옵션 좌표로 매핑
        val variantOptionPoints = createVariantOptionPoints(productOptionPoints)
        return variantOptionPoints.map { variantOptionPoint ->
            val variantOptions = variantOptionPoint.createVariantOptions(product.options)
            Variant.of(product, variantOptions, 1)
        }
    }

    private fun List<Int>.createVariantOptions(productOptions: List<ProductOption>): List<VariantOption> =
        mapIndexed { index, i ->
            val productOption = productOptions[index]
            val productOptionValue = productOption.values[i]
            VariantOption.of(productOption.name, productOptionValue)
        }

    fun createVariant(): Variant = Variant(
        id = ObjectId.get(),
        productId = ObjectId.get(),
        name = "asdad",
        options = createOption(),
        code = "",
        sku = UUID.randomUUID().toString(),
        price = Random.nextInt(10, 20) * 1000,
        stock = Random.nextInt(10, 50)
    )

    private fun createOption(): List<VariantOption> =
        List(3) { position ->
            val randomValue = Random.nextInt(0, 2)
            val name = NAMES[position]
            val value = VARIANT_MAP[name]!![randomValue]
            val price = randomValue * 250
            VariantOption(name, value, price, "test")
        }


    private fun createVariantOptionPoints(productOptionPoints: List<List<Int>>): List<List<Int>> {
        var variantOptionPoints = mutableListOf<List<Int>>()
        for (i in productOptionPoints.indices) {
            val optionPoint = productOptionPoints[i]
            variantOptionPoints = createPoint(optionPoint, variantOptionPoints).toMutableList()
        }
        return variantOptionPoints
    }

    private fun createPoint(optionPoints: List<Int>, points: List<List<Int>>): List<List<Int>> {
        if (points.isEmpty()) {
            return optionPoints.map { listOf(it) }
        }

        val result = mutableListOf<List<Int>>()
        points.forEach { point ->
            val newPoints = optionPoints.map {
                point.plus(it)
            }
            result.addAll(newPoints)
        }
        return result
    }

}