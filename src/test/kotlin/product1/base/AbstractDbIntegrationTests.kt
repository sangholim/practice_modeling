package product1.base

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import product1.config.MongoConfig

@DataMongoTest
@ContextConfiguration(classes = [MongoConfig::class])
abstract class AbstractDbIntegrationTests {

    companion object {
        val mongoDB = MongoDBContainer("mongo:4.0").apply {
            this.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl)
        }
    }
}