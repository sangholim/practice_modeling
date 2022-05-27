package product1.base

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.RabbitMQContainer

@SpringBootTest
class AbstractIntegrationTests {

    companion object {
        val mongoDB = MongoDBContainer("mongo:4.0").apply {
            this.start()
        }
        val rabbit = RabbitMQContainer("rabbitmq:3-management")
            .withUser("admin","admin")
            .withPermission("/", "admin", ".*", ".*", ".*")
            .withExposedPorts(5672, 15672).apply {
            this.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl)
            registry.add("spring.rabbitmq.host", rabbit::getContainerIpAddress)
            registry.add("spring.rabbitmq.port") { rabbit.getMappedPort(5672) }
        }
    }

}