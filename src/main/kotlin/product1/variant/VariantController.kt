package product1.variant

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = ["/products"])
class VariantController(
    private val variantService: VariantService
) {

    /**
     * 옵션 상품 조회
     * @param productId 상품 번호
     * @param query 옵션 상품 질의 필드
     */
    @GetMapping(value = ["/{productId}/variants/query"])
    suspend fun getVariant(@PathVariable productId: ObjectId, query: VariantQuery): VariantView {
        val variant = variantService.getVariantByCode(productId, query.code)
        if(variant.stock < query.count) throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        return variant.toVariantView(query.count)
    }

}