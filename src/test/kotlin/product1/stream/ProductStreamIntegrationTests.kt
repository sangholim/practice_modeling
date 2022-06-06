package product1.stream

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractIntegrationTests
import product1.config.RabbitClientConfig

class ProductStreamIntegrationTests: AbstractIntegrationTests() {

    @Autowired
    private lateinit var rabbitClientConfig: RabbitClientConfig

    /**
     * 메시지를 큐로 발송시
     * Rabbit MQ는 exchange (product)를 통해 queue(product.account)에 저장되고,
     * 저장된 queue 와 매핑됭 채널에게 전달한다.
     */
    @Test
    fun productAccountIn() = runBlocking {
        for (i in 1..100) {
            rabbitClientConfig.send("product", "product.account", ProductStreamPayload("name-$i"))
        }
        delay(5000)
    }
}