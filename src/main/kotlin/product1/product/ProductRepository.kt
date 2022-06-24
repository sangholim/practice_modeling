package product1.product

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import product1.product.domain.Product

interface ProductRepository: CoroutineCrudRepository<Product, ObjectId>