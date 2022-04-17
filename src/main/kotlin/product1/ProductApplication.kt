package product1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class ProductApplication

fun main(args: Array<String>) {
   runApplication<ProductApplication>(*args)
}