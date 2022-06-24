package product1.product.repository

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import product1.product.domain.ProductOption

interface ProductOptionRepository: CoroutineCrudRepository<ProductOption, ObjectId>