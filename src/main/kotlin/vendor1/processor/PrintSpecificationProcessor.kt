package vendor1.processor

class PrintSpecificationProcessor : Processor {

    override fun process(command: String): String? =
        vendorService.printSpecification(command)

}