package vendor1.processor

import vendor1.dto.BuyDrink

class BuyDrinkProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if(validCommand != Command.BUY) {
            return null
        }
        val payload = entityBinder<BuyDrink>(commands[1])
        return vendorService.buyDrink(payload)
    }
}