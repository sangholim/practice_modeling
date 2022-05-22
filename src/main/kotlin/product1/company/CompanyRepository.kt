package product1.company

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CompanyRepository: CoroutineCrudRepository<Company, ObjectId>