package vendor1.vendor

import vendor1.drink.*
import vendor1.dto.RegisterDrink
import vendor1.dto.ModifyDrink
import vendor1.specification.DrinkSpecification
import java.time.Instant

/**
 * 자판기
 * @property drinks 자판기 음료수 관리 데이터
 * @property specifications 음료수 명세서 데이터
 */
class Vendor {

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

    fun modifyDrink(modifyDrink: ModifyDrink): String {
        when (modifyDrink.name) {
            DrinkName.A -> modifyADrink(modifyDrink.price)
            DrinkName.B -> modifyBDrink(modifyDrink.price)
        }
        return "[변경된 음료수: $drinks]"
    }

    /**
     * 음료수 A 가격 수정
     * @param price 가격
     */
    private fun modifyADrink(price: Int) {
        val drink = (drinks[DrinkName.A] ?: throw RuntimeException("not found A drink"))
        drinks[DrinkName.A] = drink.copy(price = price)
    }

    /**
     * 음료수 B 가격 수정
     * @param price 가격
     */
    private fun modifyBDrink(price: Int) {
        val drink = (drinks[DrinkName.B] ?: throw RuntimeException("not found B drink"))
        drinks[DrinkName.B] = drink.copy(price = price)
    }

    /**
     * A 음료수 생성
     * A 음료수 명세서 생성
     * @param price 음료수 가격
     * @param count 음료수 수량
     */
    private fun createADrink(price: Int, count: Int) {
        drinks[DrinkName.A] = Drink(DrinkName.A, price, count)
        specifications[DrinkName.A] = mutableListOf()
    }

    /**
     * B 음료수 생성
     * B 음료수 명세서 생성
     * @param price 음료수 가격
     * @param count 음료수 수량
     */
    private fun createBDrink(price: Int, count: Int) {
        drinks[DrinkName.B] = Drink(DrinkName.B, price, count)
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
            throw VendorException("매진입니다")
        }
        // 음료수 존재 여부
        val drink = drinks[name] ?: throw VendorException("존재하지 않는 음료수 입니다")
        // 금액 확인
        if (amount < drink.price) {
            throw VendorException("금액이 부족합니다")
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
        val drink = drinks[name] ?: throw VendorException("존재하지 않는 음료수 입니다")
        val drinkSpecifications = specifications[name] ?: throw VendorException("존재하지 않는 음료수 입니다")
        if (drink.count == drinkSpecifications.size) {
            return true
        }
        return false
    }

    private fun createDrinkSpecification(name: DrinkName, drink: Drink) {
        // 명세서 작성
        val drinkSpecifications = specifications[name] ?: throw RuntimeException("존재하지 않는 음료수 입니다")
        val drinkSpecification = DrinkSpecification(name, drink.price)
        drinkSpecifications.add(drinkSpecification)
    }

    fun printSpecification(): String {
        val specificationBuilder = StringBuilder()
        specifications.forEach { (t, u) ->
            val count = u.size
            u.forEach { i ->
                specificationBuilder.append(i.print()).append(System.lineSeparator())
            }
            specificationBuilder.append("[음료수명:$t][총개수:$count][출력일:${Instant.now()}]").append(System.lineSeparator())
        }
        return specificationBuilder.toString()
    }
}

/**
 * 자판기 처리 예상되는 예외 클래스
 * @property message String
 * @constructor
 */
class VendorException(override val message: String) : RuntimeException(message)