package vendor1.processor

import vendor1.dto.Authentication
import vendor1.dto.VendorOperation

class StatusProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if(validCommand != Command.STATUS) {
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