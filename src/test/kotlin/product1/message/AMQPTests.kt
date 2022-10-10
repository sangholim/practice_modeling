package product1.message

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import product1.base.AbstractIntegrationTests
import product1.company.CompanyRepository
import product1.fixture.companyPayload

/**
 * amqp 테스트 결과
 * client connection blocking 방식 처리 -> reactive rabbitmqclient 바꿔야함
 * message 발송시 클래스로 정의한 body를 이용할떄, 직렬화 필수 (Serializable)
 * message listener event는 coroutine 과 호환 되지 않음
 * spring-amqp github issue에 kotlin coroutine 지원에 대한 답변은 보이지 않음
 * https://github.com/spring-projects/spring-amqp/issues/1210
 */
@Disabled
@ExperimentalCoroutinesApi
@ActiveProfiles(value = ["local","amqp"])
class AMQPTests: AbstractIntegrationTests() {

    private val topicExchange = "spring-boot-exchange"

    private val routingKey = "foo.bar.#"

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Test
    fun test() = runTest {
        rabbitTemplate.convertAndSend(topicExchange, routingKey, companyPayload())

        assert(companyRepository.findAll().count() > 0)
    }
}