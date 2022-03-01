package vendor1.drink

/**
 * 음료수 인터페이스
 */
/*
interface Drink {
    val price: Int
    val count: Int
}

 */

data class Drink(val name: DrinkName, val price: Int, val count: Int)