package product1.config

import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(
    value = [
        "product1.company",
        "product1.employee",
        "product1.category",
        "product1.shippingaddress",
        "product1.product",
        "product1.variant",
        "product1.cart",
    ]
)
@EnableReactiveMongoAuditing
class MongoConfig(
    private val mongoProperties: MongoProperties
) {

    @Bean
    fun reactiveMongoClientFactory(): ReactiveMongoDatabaseFactory =
        SimpleReactiveMongoDatabaseFactory(MongoClients.create(ConnectionString(mongoProperties.uri)), "product")

    @Bean
    fun reactiveMongoTemplate(): ReactiveMongoTemplate =
        ReactiveMongoTemplate(reactiveMongoClientFactory())
}