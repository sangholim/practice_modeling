package product1.variant

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface VariantRepository : CoroutineCrudRepository<Variant, ObjectId>