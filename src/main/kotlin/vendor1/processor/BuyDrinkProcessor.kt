package vendor1.processor

class BuyDrinkProcessor : Processor {

    override fun process(command: String): String? =
        vendorService.buyDrink(command)

}