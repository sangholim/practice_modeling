package vendor1.specification

import java.time.Instant

class ADrinkSpecification(
    val name: String,
    val price: Int,
    val createdAt: Instant
) : DrinkSpecification {
    override fun print() = "[음료수명:$name][음료수가격:$price][구매일:$createdAt]"
}