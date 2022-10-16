package product1.product

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import product1.product.domain.Product

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    /**
     * 상품 리스트 조회
     */
    fun getProducts(): Flow<Product> = productRepository.findAll()

    /**
     * 상품 조회
     * @param id 상품 번호
     */
    suspend fun getProduct(id: ObjectId): Product = productRepository.findById(id) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND
    )
}