package vendor1.processor

import vendor1.SingletonClass
import vendor1.vendor.VendorService
import java.io.OutputStream

interface Processor {
    val vendorService: VendorService
        get() = SingletonClass.vendorService

    suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean

    fun toResponseData(command: String) = (command + System.lineSeparator()).toByteArray(Charsets.UTF_8)

    fun quit(): Boolean = false
}