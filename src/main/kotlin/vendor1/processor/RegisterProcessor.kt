package vendor1.processor

import vendor1.dto.Authentication
import vendor1.dto.RegisterDrink

class RegisterProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if(validCommand != Command.REGISTER) {
            return null
        }
        val payload = entityBinder<RegisterDrink>(commands[1])
        return vendorService.registerDrink(payload)
    }

}