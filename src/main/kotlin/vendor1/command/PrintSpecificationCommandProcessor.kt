package vendor1.command

import java.io.OutputStream

class PrintSpecificationCommandProcessor : CommandProcessor {

    override suspend fun sendResponse(command: String, outputStream: OutputStream) {
        try {
            val result = vendorOperationService.printSpecification(command) ?: return
            outputStream.write(toResponseData(result))
            outputStream.flush()
        } catch (e: Exception) {
            throw RuntimeException(e.cause)
        }
    }
}