package product1.company

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(value = ["amqp"])
class CompanyMessage(
    private val companyService: CompanyService
) {

    /**
     * 메시지 수신
     * @param payload 메시지
     */
    @RabbitListener(queues = ["company"])
    suspend fun receiveMessage(payload: CompanyPayload) = companyService.create(payload)
}