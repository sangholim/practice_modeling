package vendor1.drink.dto

import vendor1.drink.DrinkName
import java.io.Serializable

/**
 * 음료 구매 커맨드
 */
data class BuyDrink(val name: DrinkName, val amount: Int) : Serializable