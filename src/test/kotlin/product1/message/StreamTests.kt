package product1.message

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import product1.base.AbstractIntegrationTests
import product1.company.CompanyRepository
import product1.fixture.companyPayload


/**
 * cloud-stream 테스트 결과
 */
@ExperimentalCoroutinesApi
@ActiveProfiles(value = ["local","stream"])
class StreamTests: AbstractIntegrationTests() {

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate

    @Test
    fun test() = runTest {
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
        rabbitTemplate.convertAndSend("company", "company", companyPayload())
    }
}