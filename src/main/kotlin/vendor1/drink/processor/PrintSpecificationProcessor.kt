package vendor1.drink.processor

import vendor1.processor.Processor
import vendor1.processor.validCommand

class PrintSpecificationProcessor : Processor {

    override fun process(data: String): String? {
        val commands = data.split(" ")
        if(!validCommand(commands[0], DrinkCommand.DRINK_SPECIFICATION)) {
            return null
        }
        return vendorService.printSpecification()
    }
}