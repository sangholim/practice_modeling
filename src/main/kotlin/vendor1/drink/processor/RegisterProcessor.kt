package vendor1.drink.processor

import vendor1.drink.dto.RegisterDrink
import vendor1.processor.Processor
import vendor1.processor.entityBinder

class RegisterProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = DrinkCommand.valueOf(commands[0])
        if(validCommand != DrinkCommand.DRINK_REGISTER) {
            return null
        }
        val payload = entityBinder<RegisterDrink>(commands[1])
        return vendorService.registerDrink(payload)
    }

}