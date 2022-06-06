package product1.stream

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.binder.test.InputDestination
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration
import org.springframework.messaging.support.MessageBuilder

@EnableAutoConfiguration(exclude = [MongoReactiveAutoConfiguration::class])
@SpringBootTest(classes = [ProductStream::class, TestChannelBinderConfiguration::class])
class ProductStreamTests {

    @Autowired
    private lateinit var inputDestination: InputDestination

    /**
     * destination 으로 정의된 채널이 없는 경우 예외
     */
    @Test
    fun couponIn() {
        val message = MessageBuilder
            .withPayload(ProductStreamPayload("name"))
            .build()
        assertDoesNotThrow { inputDestination.send(message, "product") }
    }
}