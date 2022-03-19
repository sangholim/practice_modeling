package vendor1.drink.processor

import vendor1.drink.dto.ModifyDrink
import vendor1.processor.Processor
import vendor1.processor.entityBinder

class ModifyDrinkProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = DrinkCommand.valueOf(commands[0])
        if(validCommand != DrinkCommand.DRINK_MODIFY) {
            return null
        }
        val payload = entityBinder<ModifyDrink>(commands[1])
        return vendorService.modifyDrink(payload)
    }
}