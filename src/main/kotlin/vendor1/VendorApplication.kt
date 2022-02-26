package vendor1

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.*
import vendor1.command.VendorOperation
import vendor1.vendor.Vendor
import vendor1.vendor.VendorOperationService


object SingletonClass {
    val vendor = Vendor()
    val vendorOperationService = VendorOperationService()
}

val mapper = jacksonObjectMapper()

fun main() = runBlocking {
    val vendorOperationService = SingletonClass.vendorOperationService
    val job = GlobalScope.launch {
        while (true) {
            val command = readLine() ?: ""
            vendorOperationService.setVendorStatus(command)
            if (command == "exit") {
                break
            }
        }
    }
    job.join()
}


