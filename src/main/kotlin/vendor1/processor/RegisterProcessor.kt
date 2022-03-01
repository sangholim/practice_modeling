package vendor1.processor

class RegisterProcessor : Processor {

    override fun process(command: String): String? =
        vendorService.registerDrink(command)

}