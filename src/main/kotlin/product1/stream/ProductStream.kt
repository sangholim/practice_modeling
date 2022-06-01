package product1.stream

import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Configuration
class ProductStream {

    private val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<ProductStreamPayload>()

    /**
     * 받은 메시지 처리
     */
    @Bean
    fun consume(): (Flux<ProductStreamPayload>) -> Mono<Void> = { stream ->
        stream.concatMap { payload ->
            mono { println("consumeUser: $payload") }
        }.onErrorContinue { error, _ -> println("error: $error") }.then()
    }

    /**
     * 메시지 발송
     */
    @Bean
    fun produce(): () -> Flux<ProductStreamPayload> = {
        unicastProcessor.asFlux()
    }

    /**
     * 수신 받은 후 데이터 전달
     */
    @Bean
    fun route(): (Flux<ProductStreamPayload>) -> Flux<ProductStreamPayload> = { stream ->
        stream.concatMap { payload ->
            println("route-destination: $payload")
            mono { payload }
        }.onErrorContinue { error, _ -> println("error: $error") }
    }

    /**
     * 메시지 발송
     * @param payload 메시지
     */
    fun sendMessage(payload: ProductStreamPayload) {
        unicastProcessor.emitNext(payload, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}