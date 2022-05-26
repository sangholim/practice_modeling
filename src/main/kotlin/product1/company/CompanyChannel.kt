package product1.company

import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Profile(value = ["stream"])
@Service
class CompanyChannel(
    private val company: CompanyService
) {

    @Bean
    fun consumeCompany(): Consumer<CompanyPayload> {
        return Consumer { payload: CompanyPayload ->
            mono {
                val company = company.create2(payload)
                println(company.id)
            }.subscribe()

        }
    }
}