package product1.fixture

import product1.shippingaddress.ShippingAddressPayload

inline fun shippingAddressPayload(block: ShippingAddressPayloadBuilder.() -> Unit = {}) =
    ShippingAddressPayloadBuilder().apply(block).build()

class ShippingAddressPayloadBuilder {
    var name: String = ""
    var firstRecipient: String = ""
    var secondRecipient: String? = null
    var firstPhoneNumber: String = ""
    var secondPhoneNumber: String? = null
    var zipCode: String = ""
    var line1: String = ""
    var line2: String? = null

    fun build() = ShippingAddressPayload(
        name = name,
        firstRecipient = firstRecipient,
        secondRecipient = secondRecipient,
        firstPhoneNumber = firstPhoneNumber,
        secondPhoneNumber = secondPhoneNumber,
        zipCode = zipCode,
        line1 = line1,
        line2 = line2
    )
}