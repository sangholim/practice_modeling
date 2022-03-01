package vendor1.dto

import vendor1.drink.DrinkName

/**
 * 음료수 등록
 */
class RegisterDrink(
    val type: ManagementType,
    val name: DrinkName,
    val price: Int,
    val count: Int
)