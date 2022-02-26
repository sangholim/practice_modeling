package vendor1.drink

/**
 * B 상품 음료수
 *
 * @property price 음료수 가격
 * @property count 음료수 재고
 */
data class BDrink(override val price: Int, override val count: Int) : Drink {

}