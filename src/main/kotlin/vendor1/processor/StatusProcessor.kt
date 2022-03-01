package vendor1.processor

import java.io.OutputStream

class StatusProcessor : Processor {

    override suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean {
        try {
            val result = vendorService.setVendorStatus(command) ?: return false
            outputStream.write(toResponseData(result))
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e.cause)
        }
    }

    override fun quit(payload: String): Boolean = vendorService.quit(payload)
}