package product1.company

import ch.qos.logback.core.net.ssl.SSLNestedComponentRegistryRules
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.runBlocking
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.function.Consumer


@Profile(value = ["stream"])
@Service
@EnableBinding(value = [Processor::class])
class CompanyChannel(
    private val company: CompanyService,
    private val streamBridge: StreamBridge
) {
    //val companies: Sinks.Many<CompanyPayload> = Sinks.many().multicast().onBackpressureBuffer()

    fun sendPayload(payload: CompanyPayload) = streamBridge.send("output", payload)

    @StreamListener(Processor.INPUT)
    fun receivePayload(payload: CompanyPayload) = runBlocking {
        company.create(payload)
    }
}