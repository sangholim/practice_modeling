package vendor1.processor

class ModifyDrinkProcessor : Processor {

    override fun process(command: String): String? =
        vendorService.modifyDrink(command)

}