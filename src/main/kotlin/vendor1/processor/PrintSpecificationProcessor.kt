package vendor1.processor

class PrintSpecificationProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if(validCommand != Command.SPECIFICATION) {
            return null
        }
        return vendorService.printSpecification()
    }
}