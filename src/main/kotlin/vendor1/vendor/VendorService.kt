package vendor1.vendor

import vendor1.Config
import vendor1.dto.*
import vendor1.mapper

/**
 * 자판기 운영 상태 인벤트 클래스
 * 운영시에는 운영 커맨드만 받아서 처리
 * 관리시에는 관리 커맨드만 받아서 처리
 */
class VendorService {

    private var vendorStatus: VendorStatus? = null

    private val vendor = Config.vendor

    /**
     * 음료수 구매 (거스름돈 출력)
     * @param payload 구매할 음료수 커맨드
     */
    fun buyDrink(payload: String): String? {
        try {
            if (vendorStatus != VendorStatus.RUNNING) {
                throw RuntimeException("invalid status")
            }
            val buyDrink = entityBinder<BuyDrink>(payload)
            val balance = vendor.buyDrink(buyDrink.name, buyDrink.amount)
            return "[구입한 음료수: ${buyDrink.name}], [잔여 금액: $balance]"
        } catch (e: VendorException) {
            return e.message
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
                throw RuntimeException("invalid status")
            }
            val registerDrink = entityBinder<RegisterDrink>(payload)
            if (registerDrink.type != ManagementType.REGISTER) {
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
    fun modifyDrink(payload: String): String? {
        try {
            if (vendorStatus != VendorStatus.MANAGEMENT) {
                throw RuntimeException("invalid status")
            }
            val modifyDrink = entityBinder<ModifyDrink>(payload)
            if (modifyDrink.type != ManagementType.MODIFY) {
                return null
            }

            return vendor.modifyDrink(modifyDrink)
        } catch (e: Exception) {
            return null
        }
    }

    /**
     * 거래 내역서 출력
     * @param payload String
     */
    fun printSpecification(payload: String): String? {
        try {
            if (vendorStatus != VendorStatus.MANAGEMENT) {
                throw RuntimeException("invalid status")
            }
            val printSpecification = entityBinder<PrintSpecification>(payload)
            if (printSpecification.type != ManagementType.SPECIFICATION) {
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
            if (vendorOperation.status == VendorStatus.QUIT) {
                return null
            }
            this.vendorStatus = vendorOperation.status
            return "[자판기 상태: $vendorStatus]"
        } catch (e: Exception) {
            return null
        }
    }

    fun getVendorStatus() = vendorStatus

    private inline fun <reified T> entityBinder(payload: String): T {
        try {
            return mapper.readValue(payload, T::class.java)
        } catch (e: Exception) {
            throw RuntimeException("parse-fail")
        }
    }

    fun quit(payload: String): Boolean {
        return try {
            val vendorOperation = entityBinder<VendorOperation>(payload)
            vendorOperation.status == VendorStatus.QUIT
        } catch (e: Exception) {
            false
        }
    }
}