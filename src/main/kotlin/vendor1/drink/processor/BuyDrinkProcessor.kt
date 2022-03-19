package vendor1.drink.processor

import vendor1.drink.dto.BuyDrink
import vendor1.processor.Processor
import vendor1.processor.entityBinder
import vendor1.processor.validCommand

class BuyDrinkProcessor : Processor {

    override fun process(data: String): String? {
        val commands = data.split(" ")
        if(!validCommand(commands[0], DrinkCommand.DRINK_BUY)) {
            return null
        }
        val payload = entityBinder<BuyDrink>(commands[1])
        return vendorService.buyDrink(payload)
    }
}