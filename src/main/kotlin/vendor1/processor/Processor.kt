package vendor1.processor

import vendor1.Config
import vendor1.vendor.VendorService
import java.io.OutputStream

interface Processor {
    val vendorService: VendorService
        get() = Config.vendorService

    suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean

    fun toResponseData(command: String) = (command + System.lineSeparator() + System.lineSeparator()).toByteArray(Charsets.UTF_8)

    fun quit(): Boolean = false
}