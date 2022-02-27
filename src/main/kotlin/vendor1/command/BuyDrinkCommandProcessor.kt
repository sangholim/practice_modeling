package vendor1.command

import java.io.OutputStream

class BuyDrinkCommandProcessor : CommandProcessor {

    override suspend  fun sendResponse(command: String, outputStream: OutputStream) {
        try {
            val result = vendorOperationService.buyDrink(command) ?: return
            outputStream.write(toResponseData(result))
            outputStream.flush()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}