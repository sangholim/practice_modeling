package product1.category

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CategoryRepository: MongoRepository<Category, ObjectId> {

    fun findByDepth(depth: Int): List<Category>?
}