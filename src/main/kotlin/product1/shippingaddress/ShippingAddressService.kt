package product1.shippingaddress

import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class ShippingAddressService(
    private val shippingAddressRepository: ShippingAddressRepository
) {

    /**
     * 배송지 조회
     * @param companyId 기업 번호
     */
    suspend fun getShippingAddresses(companyId: ObjectId): List<ShippingAddress> =
        shippingAddressRepository.findByCompanyId(companyId).toList().sortedByDescending { it.createAt }


    /**
     * 배송지 생성
     * @param companyId 기업 번호
     * @param payload 생성 필드
     */
    suspend fun create(companyId: ObjectId, payload: ShippingAddressPayload): ShippingAddress {
        require(shippingAddressRepository.findByCompanyId(companyId).count() < 20) { "배송지 최대 갯수 초과하였습니다." }
        return shippingAddressRepository.save(ShippingAddress.create(companyId, payload))
    }

    /**
     * 배송지 수정
     * @param id 배송지 번호
     * @param companyId 회사 번호
     * @param payload 수정 필드
     */
    suspend fun update(id: ObjectId, companyId: ObjectId, payload: ShippingAddressPayload): ShippingAddress {
        val shippingAddress = shippingAddressRepository.findById(id) ?: throw Exception("존재하지 않는 배송지 입니다.")
        require(companyId == shippingAddress.companyId) { "기업 번호가 일치하지 않습니다" }
        return shippingAddressRepository.save(shippingAddress.update(payload))
    }

    /**
     * 배송지 삭제
     * @param id 배송지 번호
     */
    suspend fun delete(id: ObjectId, companyId: ObjectId) {
        val shippingAddress = shippingAddressRepository.findById(id) ?: throw Exception("존재하지 않는 배송지 입니다.")
        require(companyId == shippingAddress.companyId) { "기업 번호가 일치하지 않습니다" }
        shippingAddressRepository.deleteById(id)
    }
}