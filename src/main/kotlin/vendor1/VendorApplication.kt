package vendor1

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.*
import vendor1.command.VendorOperation
import vendor1.vendor.Vendor
import vendor1.vendor.VendorOperationService
import java.util.*


object SingletonClass {
    val vendor = Vendor()
    val vendorOperationService = VendorOperationService()
}

val mapper = jacksonObjectMapper()

fun main() = runBlocking {
    val vendorOperationService = SingletonClass.vendorOperationService
    val job = GlobalScope.launch {
        while (true) {
            val base64Command = readLine() ?: ""
            val command = String(Base64.getDecoder().decode(base64Command))
            vendorOperationService.setVendorStatus(command)
            vendorOperationService.registerDrink(command)
            vendorOperationService.printSpecification(command)
            vendorOperationService.buyDrink(command)
            if (vendorOperationService.shutdown()) {
                break
            }
        }
    }
    job.join()
}


