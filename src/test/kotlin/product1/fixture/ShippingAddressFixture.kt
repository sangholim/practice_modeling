package product1.fixture

import org.bson.types.ObjectId
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.shippingaddress.ShippingAddressPayload

object ShippingAddressFixture {

    val id: ObjectId = ObjectId("507f1f77bcf86cd799439011")

    fun createPayload() = ShippingAddressPayload(
        name = RandomStringUtils.randomAlphabetic(20),
        firstRecipient = RandomStringUtils.randomAlphabetic(10),
        secondRecipient = RandomStringUtils.randomAlphabetic(10),
        firstPhoneNumber = RandomStringUtils.randomNumeric(10),
        secondPhoneNumber = RandomStringUtils.randomNumeric(10),
        zipCode = RandomStringUtils.randomNumeric(6),
        line1 = RandomStringUtils.randomAlphabetic(10),
        line2 = RandomStringUtils.randomAlphabetic(10)
    )
}