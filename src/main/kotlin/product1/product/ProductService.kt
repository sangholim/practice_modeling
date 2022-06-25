package product1.product

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import product1.product.domain.Product

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    /**
     * 상품 리스트 조회
     */
    fun getProducts(): Flow<Product> = productRepository.findAll()
}