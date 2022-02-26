package vendor1.vendor

import vendor1.dto.RegisterDrink
import vendor1.drink.Drink
import vendor1.drink.DrinkFactory
import vendor1.drink.DrinkName
import vendor1.specification.DrinkSpecification
import vendor1.specification.DrinkSpecificationFactory

/**
 * 자판기
 * @property drinkFactory 음료수 생성 팩토리
 * @property drinks 자판기 음료수 관리 맵 데이터
 */
class Vendor {

    private val drinkFactory = DrinkFactory()

    private val drinkSpecificationFactory = DrinkSpecificationFactory()

    private val drinks = mutableMapOf<DrinkName, Drink>()

    private val specifications = mutableMapOf<DrinkName, MutableList<DrinkSpecification>>()

    /**
     * 음료수 생성
     * @param registerDrink RegisterDrink
     */
    fun createDrink(registerDrink: RegisterDrink): String {
        when (registerDrink.name) {
            DrinkName.A -> createADrink(registerDrink.price, registerDrink.count)
            DrinkName.B -> createBDrink(registerDrink.price, registerDrink.count)
        }
        return "[등록된 음료수: $drinks]"
    }

    /**
     * A 음료수 생성
     * A 음료수 명세서 생성
     * @param price 음료수 가격
     * @param count 음료수 수량
     */
    private fun createADrink(price: Int, count: Int) {
        drinks[DrinkName.A] = drinkFactory.createDrink(DrinkName.A, price, count)
        specifications[DrinkName.A] = mutableListOf()
    }

    /**
     * B 음료수 생성
     * B 음료수 명세서 생성
     * @param price 음료수 가격
     * @param count 음료수 수량
     */
    private fun createBDrink(price: Int, count: Int) {
        drinks[DrinkName.B] = drinkFactory.createDrink(DrinkName.B, price, count)
        specifications[DrinkName.B] = mutableListOf()
    }

    /**
     * 음료수 구매
     * @param name 음료수명
     * @param amount 구매 금액
     * @return 잔액
     */
    fun buyDrink(name: DrinkName, amount: Int): Int {
        // 매진 여부
        if (soldOut(name)) {
            throw RuntimeException("매진입니다")
        }
        // 음료수 존재 여부
        val drink = drinks[name] ?: throw RuntimeException("존재하지 않는 음료수 입니다")
        // 금액 확인
        if (amount < drink.price) {
            throw RuntimeException("금액이 부족합니다")
        }
        // 구매
        val balance = amount - drink.price
        // 명세서 작성
        createDrinkSpecification(name, drink)
        return balance
    }

    /**
     * 음료수 매진 여부 확인
     * @param name DrinkName
     * @return Boolean
     */
    private fun soldOut(name: DrinkName): Boolean {
        val drink = drinks[name] ?: throw RuntimeException("존재하지 않는 음료수 입니다")
        val drinkSpecifications = specifications[name] ?: throw RuntimeException("존재하지 않는 음료수 입니다")
        if (drink.count == drinkSpecifications.size) {
            return true
        }
        return false
    }

    private fun createDrinkSpecification(name: DrinkName, drink: Drink) {
        // 명세서 작성
        val drinkSpecifications = specifications[name] ?: throw RuntimeException("존재하지 않는 음료수 입니다")
        val drinkSpecification = drinkSpecificationFactory.createDrinkSpecification(name, drink.price)
        drinkSpecifications.add(drinkSpecification)
    }

    fun printSpecification():String {
        val specificationBuilder = StringBuilder()
        specifications.forEach { (t, u) ->
            var count = 0
            u.forEach { i ->
                specificationBuilder.append(i.print()).append("\r\n")
                count++
            }
            specificationBuilder.append("[음료수명: $t][판매 개수: $count]")
        }
        return specificationBuilder.toString()
    }
}