package vendor1.drink.processor

import vendor1.drink.dto.RegisterDrink
import vendor1.processor.Processor
import vendor1.processor.entityBinder
import vendor1.processor.validCommand

class RegisterProcessor : Processor {

    override fun process(data: String): String? {
        val commands = data.split(" ")
        if(!validCommand(commands[0], DrinkCommand.DRINK_REGISTER)) {
            return null
        }
        val payload = entityBinder<RegisterDrink>(commands[1])
        return vendorService.registerDrink(payload)
    }

}