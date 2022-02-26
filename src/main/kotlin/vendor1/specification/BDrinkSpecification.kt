package vendor1.specification

import java.time.Instant

class BDrinkSpecification(
    val name: String,
    val price: Int,
    val createdAt: Instant
) : DrinkSpecification {
}