package vendor1.drink.processor

import vendor1.processor.Processor

class PrintSpecificationProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = DrinkCommand.valueOf(commands[0])
        if(validCommand != DrinkCommand.DRINK_SPECIFICATION) {
            return null
        }
        return vendorService.printSpecification()
    }
}