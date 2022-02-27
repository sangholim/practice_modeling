package vendor1.command

import vendor1.SingletonClass
import vendor1.vendor.VendorOperationService
import java.io.OutputStream

interface CommandProcessor {
    val vendorOperationService: VendorOperationService
        get() = SingletonClass.vendorOperationService

    suspend fun sendResponse(command: String, outputStream: OutputStream)

    fun toResponseData(command: String) = (command + System.lineSeparator()).toByteArray(Charsets.UTF_8)
}