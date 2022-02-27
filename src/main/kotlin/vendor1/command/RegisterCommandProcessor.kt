package vendor1.command

import java.io.OutputStream

class RegisterCommandProcessor : CommandProcessor {

    override suspend  fun sendResponse(command: String, outputStream: OutputStream) {
        try {
            val result = vendorOperationService.registerDrink(command) ?: return
            outputStream.write(toResponseData(result))
            outputStream.flush()
        } catch (e: Exception) {
            throw RuntimeException(e.cause)
        }
    }
}