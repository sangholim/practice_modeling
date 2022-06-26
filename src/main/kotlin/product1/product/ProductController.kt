package product1.product

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import product1.product.domain.Product
import product1.product.dto.ProductSummaryView
import product1.product.dto.ProductView
import product1.product.dto.toProductSummaryView
import product1.product.dto.toProductView

@RestController
@RequestMapping(value = ["/products"])
class ProductController(
    private val productService: ProductService
) {

    /**
     * 상품 리스트 조회
     */
    @GetMapping(value = [""])
    fun getProducts(): Flow<ProductSummaryView> = productService.getProducts().map(Product::toProductSummaryView)

    /**
     * 상품 조회
     * @param id 상품 번호
     */
    @GetMapping(value = ["/{id}"])
    suspend fun getProduct(@PathVariable id: ObjectId): ProductView = productService.getProduct(id).toProductView()
}