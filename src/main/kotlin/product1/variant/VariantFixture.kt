package product1.variant

import org.bson.types.ObjectId
import java.util.*
import kotlin.random.Random

object VariantFixture {

    private val names = listOf("size", "color", "design")

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


    fun createVariant(): Variant = Variant(
        id = ObjectId.get(),
        options = createOption(),
        sku = UUID.randomUUID().toString(),
        price = Random.nextInt(10, 20) * 1000,
        stock = Random.nextInt(10, 50)
    )

    private fun createOption(): Set<VariantOption> =
        List(3) { position ->
            val randomValue = Random.nextInt(0, 2)
            val name = names[position]
            val value = VARIANT_MAP[name]!![randomValue]
            VariantOption(name, value)
        }.toSet()

}