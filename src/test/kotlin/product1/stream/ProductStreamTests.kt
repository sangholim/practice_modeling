package product1.stream

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractIntegrationTests

class ProductStreamTests: AbstractIntegrationTests() {

    @Autowired
    private lateinit var productStream: ProductStream

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate

    @BeforeEach
    fun setup() = runBlocking {
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
    }

    /**
     * 메시지를 큐로 발송시
     * Rabbit MQ는 exchage를 통해 queue에 저장되고,
     * 저장된 queue consumer 대상에게 전달한다.
     * consumer 대상은 메시지를 받고 해당 메시지는 db에 저장된다.
     */
    @Test
    fun produce() = runBlocking {
        for (i in 1..100) {
            productStream.sendMessage(ProductStreamPayload("name-$i"))
        }
        delay(5000)
    }

    /**
     * 메시지를 큐로 발송시
     * rabbitTemplate 로 route-destination 으로 발송
     * route-in [수신 채널]은 user-destination 로 발송
     * consume [수신 채널]은 데이터를 db에 저장
     */
    @Test
    fun route() = runBlocking {
        for (i in 1..100) {
            rabbitTemplate.convertAndSend("route-destination","", ProductStreamPayload("name-$i"))
        }
        delay(5000)
    }

}