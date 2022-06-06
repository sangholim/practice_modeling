package product1.base

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.RabbitMQContainer
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

@SpringBootTest
abstract class AbstractIntegrationTests {

    companion object {
        private val dockerRabbitMqDirectory: Path = Paths.get("docker/rabbitMQ")
        val mongoDB = MongoDBContainer("mongo:4.0").apply {
            this.start()
        }
        val rabbit = RabbitMQContainer("rabbitmq:3-management")
            .withFileSystemBind(dockerRabbitMqDirectory.resolve("rabbitmq.conf").absolutePathString(),
                "/etc/rabbitmq/rabbitmq.conf", BindMode.READ_ONLY)
            .withFileSystemBind(dockerRabbitMqDirectory.resolve("definitions.json").absolutePathString(),
                "/etc/rabbitmq/definitions.json", BindMode.READ_ONLY)
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