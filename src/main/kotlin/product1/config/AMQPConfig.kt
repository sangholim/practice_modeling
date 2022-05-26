package product1.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import product1.company.CompanyMessage

@Profile(value = ["amqp"])
@Configuration
class AMQPConfig {

    private val topicExchange = "spring-boot-exchange"

    private val routingKey = "foo.bar.#"

    private val queueName = "company"

    @Bean
    fun companyQueue() = Queue(queueName, false)

    @Bean
    fun exchange() = TopicExchange(topicExchange)


    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey)
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): SimpleMessageListenerContainer = SimpleMessageListenerContainer().apply {
        this.connectionFactory = connectionFactory
        setQueueNames(queueName)
        setMessageListener(listenerAdapter)

    }

    @Bean
    fun listenerAdapter(companyMessage: CompanyMessage): MessageListenerAdapter =
        MessageListenerAdapter(companyMessage, "receiveMessage")

}