package vendor1.processor

import java.io.OutputStream

class PrintSpecificationCommandProcessor : CommandProcessor {

    override suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean {
        try {
            val result = vendorService.printSpecification(command) ?: return false
            outputStream.write(toResponseData(result))
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e.cause)
        }
    }
}