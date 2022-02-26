package vendor1.drink

/**
 * A 음료수
 *
 * @property price 음료수 가격
 * @property count 음료수 재고
 */
data class ADrink(override val price: Int, override val count: Int) : Drink