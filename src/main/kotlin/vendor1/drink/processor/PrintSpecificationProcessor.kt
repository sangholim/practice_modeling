package vendor1.drink.processor

import vendor1.processor.Processor
import vendor1.processor.entityBinder

class PrintSpecificationProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if(validCommand != Command.DRINK_SPECIFICATION) {
            return null
        }
        return vendorService.printSpecification()
    }
}