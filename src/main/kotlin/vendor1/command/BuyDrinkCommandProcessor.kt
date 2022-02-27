package vendor1.command

import java.io.OutputStream

class BuyDrinkCommandProcessor : CommandProcessor {

    override suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean {
        try {
            val result = vendorOperationService.buyDrink(command) ?: return false
            outputStream.write(toResponseData(result))
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}