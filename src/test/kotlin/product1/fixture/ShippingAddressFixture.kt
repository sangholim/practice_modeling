package product1.fixture

import org.bson.types.ObjectId
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils
import product1.shippingaddress.ShippingAddress
import product1.shippingaddress.ShippingAddressPayload
import java.time.Instant

object ShippingAddressFixture {

    val id: ObjectId = ObjectId("507f1f77bcf86cd799439011")

    fun createInvalidParams() = ShippingAddressPayload(
        name = RandomStringUtils.randomAlphabetic(30),
        firstRecipient = RandomStringUtils.randomAlphabetic(30),
        secondRecipient = RandomStringUtils.randomAlphabetic(30),
        firstPhoneNumber = RandomStringUtils.randomNumeric(30),
        secondPhoneNumber = RandomStringUtils.randomNumeric(30),
        zipCode = RandomStringUtils.randomNumeric(30),
        line1 = "",
        line2 = ""
    )


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

    fun createShippingAddress(companyId: ObjectId) =
        ShippingAddress.create(companyId, createPayload())
            .copy(
                id = ObjectId.get(),
                createAt = Instant.now()
            )
}