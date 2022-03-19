package vendor1.vendor.processor

import vendor1.Command
import vendor1.account.dto.Authentication
import vendor1.drink.dto.VendorOperation
import vendor1.drink.processor.DrinkCommand
import vendor1.processor.Processor
import vendor1.processor.entityBinder
import vendor1.processor.validCommand

class StatusProcessor : Processor {

    override fun process(data: String): String? {
        val commands = data.split(" ")
        if(!validCommand(commands[0], Command.STATUS)) {
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