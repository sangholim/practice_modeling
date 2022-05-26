package product1.message

import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import product1.base.AbstractIntegrationTests
import product1.company.CompanyChannel
import product1.fixture.CompanyFixture

/**
 * cloud-stream 테스트 결과
 */
@ActiveProfiles(value = ["local","stream"])
class StreamTests: AbstractIntegrationTests() {

    @Autowired
    private lateinit var companyChannel: CompanyChannel

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate

    @Test
    fun test() {
        //companyChannel.sendPayload("test11111")
        //rabbitTemplate.convertAndSend("company", "output", CompanyFixture.createCompanyPayload())
        companyChannel.sendPayload(CompanyFixture.createCompanyPayload())
    }
}