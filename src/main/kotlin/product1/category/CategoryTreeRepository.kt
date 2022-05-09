package product1.category

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryTreeRepository: MongoRepository<CategoryTree, ObjectId>