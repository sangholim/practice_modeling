package vendor1.specification

import java.time.Instant

class ADrinkSpecification(
    val name: String,
    val price: Int,
    val createdAt: Instant
): DrinkSpecification {
}