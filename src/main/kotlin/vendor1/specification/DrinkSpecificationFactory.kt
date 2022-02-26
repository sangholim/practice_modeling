package vendor1.specification

import vendor1.drink.DrinkName
import java.time.Instant

/**
 * 다양한 음료수 명세서 객체 관리 클래스
 */
class DrinkSpecificationFactory {

    fun createDrinkSpecification(name: DrinkName, price: Int) =
        when (name) {
            DrinkName.A -> ADrinkSpecification(name.name, price, Instant.now())
            DrinkName.B -> BDrinkSpecification(name.name, price, Instant.now())
        }
}