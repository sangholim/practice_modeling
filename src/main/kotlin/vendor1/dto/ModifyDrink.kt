package vendor1.dto

import vendor1.drink.DrinkName

class ModifyDrink(
    val type: ManagementType,
    val name: DrinkName,
    val price: Int
)