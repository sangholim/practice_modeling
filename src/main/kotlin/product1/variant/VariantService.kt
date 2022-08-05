package product1.variant

import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class VariantService(
    private val variantRepository: VariantRepository
) {

    /**
     * 옵션이 포함되어 있는 상품 찾기
     * @param productId 상품 번호
     * @param code 코드 (구분자 ,)
     */
    suspend fun getVariantByCode(productId: ObjectId, code: String): Variant? {
        return variantRepository.findByProductId(productId).firstOrNull { it.code == code }
    }
}