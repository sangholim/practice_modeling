package product1.shippingaddress

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ShippingAddressRepository : CoroutineCrudRepository<ShippingAddress, ObjectId> {

    /**
     * 기업 번호 기준 배송지 리스트 검색
     * @param companyId 배송지 리스트
     */
    fun findByCompanyId(companyId: ObjectId): Flow<ShippingAddress>
}