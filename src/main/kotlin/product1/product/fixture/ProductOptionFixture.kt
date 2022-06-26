package product1.product.fixture

import product1.TestDataLoader
import product1.product.domain.ProductOption
import product1.product.domain.ProductOptionType
import kotlin.random.Random

object ProductOptionFixture {

    private val OPTION_NAMES = listOf("size", "color")

    fun createOptions(): List<ProductOption> {
        val list = mutableListOf<ProductOption>()
        for (i in 1..10) {
            val index = i % 2
            list.add(createByTypeAndName(ProductOptionType.COMBINATION, OPTION_NAMES[index]))
            list.add(createByTypeAndName(ProductOptionType.EXTRA, "추가 상품"))
        }
        return list
    }

    private fun createByTypeAndName(type: ProductOptionType, name: String) = ProductOption(
        type = type,
        name = name,
        value = TestDataLoader.generateAlphabet(5),
        code = TestDataLoader.generateAlphabet(5),
        price = Random.nextInt(1, 100) * 100
    )

}