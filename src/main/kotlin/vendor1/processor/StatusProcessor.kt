package vendor1.processor

class StatusProcessor : Processor {

    override fun process(command: String): String? =
        vendorService.setVendorStatus(command)

    override fun quit(payload: String): Boolean = vendorService.quit(payload)
}