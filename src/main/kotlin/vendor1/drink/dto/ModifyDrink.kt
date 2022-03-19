package vendor1.drink.dto

import vendor1.drink.DrinkName
import java.io.Serializable

data class ModifyDrink(
    val name: DrinkName,
    val price: Int
): Serializable