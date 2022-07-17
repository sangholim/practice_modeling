package product1.variant

import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/products"])
class VariantController(
    private val variantService: VariantService
) {

    /**
     * 코드로 옵션 상품 조회
     * @param productId 상품 번호
     * @param code '-' 구분자로 구성된  옵션 상품 코드
     */
    @GetMapping(value = ["/{productId}/variants/code/{code}"])
    suspend fun getVariantByCode(@PathVariable productId: ObjectId, @PathVariable code: String): Variant? =
        variantService.getVariantByProductIdAndCode(productId, code)


    /**
     * 옵션 상품 조회
     * @productId 상품 번호
     * @param id 옵션 상품 번호
     */
    @GetMapping(value = ["/{productId}/variants/{id}"])
    suspend fun getVariant(@PathVariable productId: ObjectId, id: ObjectId): Variant? = null

}