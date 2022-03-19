package vendor1.drink.processor

import vendor1.drink.dto.ModifyDrink
import vendor1.processor.Processor
import vendor1.processor.entityBinder
import vendor1.processor.validCommand

class ModifyDrinkProcessor : Processor {

    override fun process(data: String): String? {
        val commands = data.split(" ")
        if(!validCommand(commands[0], DrinkCommand.DRINK_MODIFY)) {
            return null
        }
        val payload = entityBinder<ModifyDrink>(commands[1])
        return vendorService.modifyDrink(payload)
    }
}