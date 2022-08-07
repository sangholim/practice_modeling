package product1.variant

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface VariantRepository : CoroutineCrudRepository<Variant, ObjectId> {

    fun findByProductId(productId: ObjectId): Flow<Variant>

    fun findByIdsIn(ids: List<ObjectId>): Flow<Variant>
}