package product1.variant

import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VariantService(
    private val variantRepository: VariantRepository
) {

    /**
     * 코드로 옵션 상품 조회
     * @param productId 상품 번호
     * @param code 코드
     */
    suspend fun getVariantByCode(productId: ObjectId, code: String): Variant {
        return variantRepository.findByProductId(productId).firstOrNull { it.code == code } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}