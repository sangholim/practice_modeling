package vendor1.dto

import vendor1.drink.DrinkName

/**
 * 음료 구매 커맨드
 */
class BuyDrink(val name: DrinkName, val amount: Int) {
}