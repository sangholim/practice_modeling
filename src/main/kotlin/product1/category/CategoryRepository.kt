package product1.category

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CategoryRepository: CoroutineCrudRepository<Category, ObjectId> {

    fun findByDepth(depth: Int): Flow<Category>
}