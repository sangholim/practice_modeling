package vendor1.vendor

import vendor1.SingletonClass
import vendor1.dto.*
import vendor1.mapper

/**
 * 자판기 운영 상태 인벤트 클래스
 * 운영시에는 운영 커맨드만 받아서 처리
 * 관리시에는 관리 커맨드만 받아서 처리
 */
class VendorOperationService {

    private var vendorStatus: VendorStatus? = null

    private val vendor = SingletonClass.vendor

    /**
     * 음료수 구매 (거스름돈 출력)
     * @param payload 구매할 음료수 커맨드
     */
    fun buyDrink(payload: String): String? {
        try {
            if (vendorStatus != VendorStatus.RUNNING) {
                return null
            }
            val buyDrink = entityBinder<BuyDrink>(payload)
            val balance = vendor.buyDrink(buyDrink.name, buyDrink.amount)
            return "[구입한 음료수: ${buyDrink.name}], [잔여 금액: $balance]"
        } catch (e: VendorException) {
            return e.message
        } catch (e: VendorOperationServiceException) {
          return null
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e.cause)
        }
    }

    /**
     * 음료수 등록
     * @param payload 등록할 음료수 커맨드
     */
    fun registerDrink(payload: String): String? {
        try {
            if (vendorStatus != VendorStatus.MANAGEMENT) {
                return null
            }
            val registerDrink = entityBinder<RegisterDrink>(payload)
            if (registerDrink.type != DrinkManagementType.REGISTER) {
                return null
            }
            return vendor.createDrink(registerDrink)

        } catch (e: Exception) {
            return null
            //throw RuntimeException("등록시 문제 발생")
        }
    }

    /**
     * 음료수 변경
     * @param payload 변경할 음료수 데이터
     */
    fun changeDrink(payload: String) {
        if (vendorStatus != VendorStatus.MANAGEMENT) {
            return
        }
        val changeDrink = entityBinder<ChangeDrink>(payload)
        if (changeDrink.type != DrinkManagementType.CHANGE) {
            return
        }
    }

    /**
     * 거래 내역서 출력
     * @param payload String
     */
    fun printSpecification(payload: String):String? {
        try {
            if (vendorStatus != VendorStatus.MANAGEMENT) {
                return null
            }
            val printDrinkSpecification = entityBinder<PrintDrinkSpecification>(payload)
            if (printDrinkSpecification.type != DrinkManagementType.SPECIFICATION) {
                return null
            }
            return vendor.printSpecification()
        } catch (e: Exception) {
            return null
        }
    }

    /**
     * 자판기 상태 설정
     * @param payload 자판기 상태 커맨드
     */
    fun setVendorStatus(payload: String): String? {
        try {
            val vendorOperation = entityBinder<VendorOperation>(payload)
            this.vendorStatus = vendorOperation.status
            return "[자판기 상태: $vendorStatus]"
        } catch (e: Exception) {
            return null
        }
    }

    private inline fun <reified T> entityBinder(payload: String): T {
        try {
            return mapper.readValue(payload, T::class.java)
        } catch (e: Exception) {
            throw VendorOperationServiceException("parse-fail")
        }

    }

    fun shutdown() = vendorStatus == VendorStatus.SHUTDOWN

}

class VendorOperationServiceException(override val message: String) : RuntimeException()