package vendor1.drink

/**
 * 다양한 음료수 객체 관리 클래스
 */
class DrinkFactory {

    fun createDrink(name: DrinkName, price: Int, count:Int) =
        when (name) {
            DrinkName.A -> ADrink(price, count)
            DrinkName.B -> BDrink(price, count)
        }
}