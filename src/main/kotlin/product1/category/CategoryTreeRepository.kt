package product1.category

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CategoryTreeRepository: CoroutineCrudRepository<CategoryTree, ObjectId>