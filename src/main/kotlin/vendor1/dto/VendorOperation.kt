package vendor1.dto

import vendor1.vendor.VendorStatus
import java.io.Serializable

data class VendorOperation(
    val status: VendorStatus
) : Serializable