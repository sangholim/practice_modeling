package vendor1.command

import java.io.OutputStream

class StatusCommandProcessor : CommandProcessor {

    override suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean {
        try {
            val result = vendorOperationService.setVendorStatus(command) ?: return false
            outputStream.write(toResponseData(result))
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e.cause)
        }
    }

    override fun quit(): Boolean = vendorOperationService.quit()
}