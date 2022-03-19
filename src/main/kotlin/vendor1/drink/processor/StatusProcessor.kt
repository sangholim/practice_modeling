package vendor1.drink.processor

import vendor1.account.dto.Authentication
import vendor1.drink.dto.VendorOperation
import vendor1.processor.Processor
import vendor1.processor.entityBinder

class StatusProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = DrinkCommand.valueOf(commands[0])
        if(validCommand != DrinkCommand.STATUS) {
            return null
        }
        val authentication = entityBinder<Authentication>(commands[2])
        isAdmin(authentication)
        val payload = entityBinder<VendorOperation>(commands[1])
        return vendorService.setVendorStatus(payload)
    }

    private fun isAdmin(authentication: Authentication): Boolean {
        if (!(authentication.username == "test" && authentication.password == "test")) {
            throw RuntimeException("unauthorized")
        }
        return true
    }
}