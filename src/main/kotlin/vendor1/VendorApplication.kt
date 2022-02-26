package vendor1

import vendor1.vendor.Vendor
import vendor1.vendor.VendorStatus

fun main(args: Array<String>) {

    val status = VendorStatus.RUNNING
    runningVendor(status)
    managementVendor(status)
}

fun runningVendor(status: VendorStatus) {
    if (status == VendorStatus.MANAGEMENT) {
        return
    }
    // 사용자 구매 시나리오
    //vendor.buyDrink()
}

fun managementVendor(status: VendorStatus) {
    if (status == VendorStatus.RUNNING) {
        return
    }
    // 관리 시나리오
    //vendor.createADrink()
    //vendor.createBDrink()
}