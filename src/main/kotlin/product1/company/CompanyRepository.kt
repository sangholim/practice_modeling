package product1.company

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface CompanyRepository: CoroutineSortingRepository<Company, ObjectId>