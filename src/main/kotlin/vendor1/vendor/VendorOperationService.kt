package vendor1.vendor

import vendor1.SingletonClass
import vendor1.command.BuyDrink
import vendor1.command.ChangeDrink
import vendor1.command.RegisterDrink
import vendor1.command.VendorOperation
import vendor1.mapper

/**
 * 자판기 운영 상태 인벤트 클래스
 * 운영시에는 운영 커맨드만 받아서 처리
 * 관리시에는 관리 커맨드만 받아서 처리
 */
class VendorOperationService {

    private var vendorStatus: VendorStatus? = null

    /**
     * 음료수 구매
     * @param buyDrink 구매할 음료수
     */
    fun buyDrink(buyDrink: BuyDrink) {
        if (vendorStatus != VendorStatus.RUNNING) {
            return
        }
        val vendor = SingletonClass.vendor
        // 상품 구매
    }

    /**
     * 음료수 등록
     * @param registerDrink 등록할 음료수
     */
    fun registerDrink(registerDrink: RegisterDrink) {
        if (vendorStatus != VendorStatus.MANAGEMENT) {
            return
        }
        // 상품 등록
    }

    /**
     * 음료수 변경
     * @param changeDrink 변경할 음료수
     */
    fun changeDrink(changeDrink: ChangeDrink) {
        if (vendorStatus != VendorStatus.MANAGEMENT) {
            return
        }
    }

    fun setVendorStatus(payload: String) {
        try {
            val vendorOperation = mapper.readValue(payload, VendorOperation::class.java)
            this.vendorStatus = vendorOperation.status
        } catch (e: Exception) {
            return
        }

    }
}