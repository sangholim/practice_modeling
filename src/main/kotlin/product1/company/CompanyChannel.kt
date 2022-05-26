package product1.company

import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Profile(value = ["stream"])
@Service
class CompanyChannel(
    private val company: CompanyService
) {

    @Bean
    fun consumeCompany(): (Flux<CompanyPayload>) -> Mono<Void> = { stream ->
        stream.concatMap { payload ->
            mono {
                val result = company.create2(payload)
                println(result.id)
            }
        }.then()
    }

}