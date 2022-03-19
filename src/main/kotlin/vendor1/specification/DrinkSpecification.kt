package vendor1.specification

import vendor1.drink.domain.DrinkName
import java.time.Instant

/**
 * 음료수 명세서
 */
class DrinkSpecification(
    val name: DrinkName,
    val price: Int
) {
    private val createdAt = Instant.now()
    fun print() = "[음료수명:$name][음료수가격:$price][구매일:$createdAt]"
}