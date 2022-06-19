package product1.shippingaddress

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/companies"])
class ShippingAddressController(
    private val shippingAddressService: ShippingAddressService
) {

    @GetMapping(value = ["/{companyId}/shipping-addresses"])
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun getShippingAddresses(@PathVariable companyId: ObjectId): List<ShippingAddressView> =
        shippingAddressService.getShippingAddresses(companyId).map(ShippingAddress::toShippingAddressView)

    @PostMapping(value = ["/{companyId}/shipping-addresses"])
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun createShippingAddress(
        @PathVariable companyId: ObjectId,
        @Valid @RequestBody payload: ShippingAddressPayload
    ) {
        shippingAddressService.create(companyId, payload)
    }

    @PutMapping(value = ["/{companyId}/shipping-addresses/{id}"])
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun updateShippingAddress(
        @PathVariable companyId: ObjectId,
        @PathVariable id: ObjectId,
        @Valid @RequestBody payload: ShippingAddressPayload
    ) {
        shippingAddressService.update(id, companyId, payload)
    }

    @DeleteMapping(value = ["/{companyId}/shipping-addresses/{id}"])
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun deleteShippingAddress(
        @PathVariable companyId: ObjectId,
        @PathVariable id: ObjectId
    ) {
        shippingAddressService.delete(id, companyId)
    }
}