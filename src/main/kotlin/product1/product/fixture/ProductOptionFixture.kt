package product1.product.fixture

import product1.TestDataLoader
import product1.product.domain.ProductOption
import product1.product.domain.ProductOptionType
import kotlin.random.Random

object ProductOptionFixture {

    private val OPTION_NAMES = listOf("size", "color")

    fun createOptions(): List<ProductOption> = List(10) {
        val index = it % 2
        createByTypeAndName(ProductOptionType.COMBINATION, OPTION_NAMES[index])
    }


    fun createExtras(): List<ProductOption> = List(10) {
        createByTypeAndName(ProductOptionType.EXTRA, "추가 상품")
    }

    private fun createByTypeAndName(type: ProductOptionType, name: String) = ProductOption(
        type = type,
        name = name,
        value = TestDataLoader.generateAlphabet(5),
        code = TestDataLoader.generateAlphabet(5),
        price = Random.nextInt(1, 100) * 100
    )

}