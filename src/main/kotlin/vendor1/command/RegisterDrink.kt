package vendor1.command

import vendor1.drink.DrinkName

/**
 * 음료수 등록
 */
class RegisterDrink(
    val name: DrinkName,
    val price: Int,
    val count: Int
)