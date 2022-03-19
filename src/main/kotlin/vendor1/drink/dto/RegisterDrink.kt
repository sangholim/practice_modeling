package vendor1.drink.dto

import vendor1.drink.DrinkName
import java.io.Serializable

/**
 * 음료수 등록
 */
data class RegisterDrink(
    val name: DrinkName,
    val price: Int,
    val count: Int
): Serializable