package vendor1.processor

import java.io.OutputStream

class BuyDrinkCommandProcessor : CommandProcessor {

    override suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean {
        try {
            val result = vendorService.buyDrink(command) ?: return false
            outputStream.write(toResponseData(result))
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}