package product1.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.boot.autoconfigure.amqp.RabbitProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.rabbitmq.OutboundMessage
import reactor.rabbitmq.RabbitFlux
import reactor.rabbitmq.Sender
import reactor.rabbitmq.SenderOptions
import javax.annotation.PreDestroy


@Configuration
class RabbitClientConfig {

    private lateinit var connection: Mono<Connection>

    private lateinit var sender: Sender

    private var objectMapper = jacksonObjectMapper()

    @Bean
    fun connectionMono(rabbitProperties: RabbitProperties): Mono<Connection> {
        val connectionFactory = ConnectionFactory().apply {
            this.host = rabbitProperties.host
            this.port = rabbitProperties.port
            this.username = rabbitProperties.username
            this.password = rabbitProperties.password
        }
        connection = Mono.fromCallable { connectionFactory.newConnection("reactor-rabbit") }.cache()
        return connection
    }

    @Bean
    fun sender(connectionMono: Mono<Connection>): Sender {
        sender = RabbitFlux.createSender(SenderOptions().connectionMono(connectionMono))
        return sender
    }

    fun send(exchange: String, payload: Any) = sender.send(
        Flux.just(
            OutboundMessage(
                exchange,
                "",
                objectMapper.writeValueAsBytes(payload)
            )
        )
    ).subscribe()

    fun send(exchange: String, route: String, payload: Any) = sender.send(
        Flux.just(
            OutboundMessage(
                exchange,
                route,
                objectMapper.writeValueAsBytes(payload)
            )
        )
    ).subscribe()


    @PreDestroy
    fun close(){
        connection.block()?.close()
    }
}