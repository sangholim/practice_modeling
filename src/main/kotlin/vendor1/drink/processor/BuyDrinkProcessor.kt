package vendor1.drink.processor

import vendor1.drink.dto.BuyDrink
import vendor1.processor.Processor
import vendor1.processor.entityBinder

class BuyDrinkProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = DrinkCommand.valueOf(commands[0])
        if(validCommand != DrinkCommand.DRINK_BUY) {
            return null
        }
        val payload = entityBinder<BuyDrink>(commands[1])
        return vendorService.buyDrink(payload)
    }
}