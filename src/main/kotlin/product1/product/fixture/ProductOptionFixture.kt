package product1.product.fixture

import product1.product.domain.ProductOption
import product1.product.domain.ProductOptionValue
import kotlin.random.Random

object ProductOptionFixture {

    private val NAMES = listOf("size", "color", "design")

    private val SIZES = listOf("L", "M", "S")

    private val COLORS = listOf("WHITE", "BLACK", "GREEN")

    private val DESIGNS = listOf("STRIPED", "PLAIN", "DOTTED")

    private val OPTION_MAP = mapOf(
        "size" to SIZES,
        "color" to COLORS,
        "design" to DESIGNS
    )

    private val OPTION_NAMES = listOf("size", "color")

    fun getProductOptions(): List<ProductOption> {
        while (true) {
            createProductOptions().let { productOptions ->
                if (productOptions.isDuplicatedCode()) return@let
                return productOptions
            }
        }
    }

    private fun List<ProductOption>.isDuplicatedCode(): Boolean =
        flatMap { it.values }.groupingBy { it.code }.eachCount().filter { it.value > 1 }.isNotEmpty()

    private fun createProductOptions(): List<ProductOption> = OPTION_MAP.map { option ->
        val name = option.key
        val values = option.value
        ProductOption.ofCOMBINATION(name, values.createProductOptionValues())
    }

    private fun List<String>.createProductOptionValues(): List<ProductOptionValue> = map { value ->
        ProductOptionValue.of(value, Random.nextInt(2, 5) * 100)
    }
}