package vendor1.vendor

import vendor1.Config
import vendor1.drink.dto.BuyDrink
import vendor1.drink.dto.ModifyDrink
import vendor1.drink.dto.RegisterDrink
import vendor1.drink.dto.VendorOperation
import vendor1.dto.*

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
    fun buyDrink(payload: BuyDrink): String {
        if (vendorStatus != VendorStatus.RUNNING) {
            throw RuntimeException("invalid status")
        }

        return try {
            val balance = vendor.buyDrink(payload.name, payload.amount)
            "[구입한 음료수: ${payload.name}], [잔여 금액: $balance]"
        } catch (e: VendorException) {
            "${e.message}"
        }

    }

    /**
     * 음료수 등록
     * @param payload 등록할 음료수 커맨드
     */
    fun registerDrink(payload: RegisterDrink): String {
        if (vendorStatus != VendorStatus.MANAGEMENT) {
            throw RuntimeException("invalid status")
        }
        return vendor.createDrink(payload)
    }

    /**
     * 음료수 변경
     * @param payload 변경할 음료수 데이터
     */
    fun modifyDrink(payload: ModifyDrink): String {
        if (vendorStatus != VendorStatus.MANAGEMENT) {
            throw RuntimeException("invalid status")
        }
        return vendor.modifyDrink(payload)
    }

    /**
     * 거래 내역서 출력
     * @param payload 명세서 출력
     */
    fun printSpecification(): String {
        if (vendorStatus != VendorStatus.MANAGEMENT) {
            throw RuntimeException("invalid status")
        }
        return vendor.printSpecification()
    }

    /**
     * 자판기 상태 설정
     * @param payload 자판기 상태 커맨드
     */
    fun setVendorStatus(payload: VendorOperation): String {
        this.vendorStatus = payload.status
        return "[자판기 상태: $vendorStatus]"
    }

    fun getVendorStatus() = vendorStatus

}