package product1.stream

import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class ProductStream {

    /**
     * 받은 메시지 처리
     */
    @Bean
    fun productAccount(): (Flux<ProductStreamPayload>) -> Mono<Void> = { stream ->
        stream.concatMap { payload ->
            mono { println("productAccount: $payload") }
        }.onErrorContinue { error, _ -> println("error: $error") }.then()
    }

}