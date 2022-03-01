package vendor1.processor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import vendor1.Config
import vendor1.vendor.VendorService
import vendor1.vendor.VendorStatus
import java.io.OutputStream

class ProcessorFactory {
    private val vendorService: VendorService
        get() = Config.vendorService

    companion object {
        private var runningProcessors: Flow<Processor>? = null
        private var managementProcessors: Flow<Processor>? = null
        private var statusProcessor: Processor? = null

        fun getRunningInstance(): Flow<Processor>? {
            if (runningProcessors != null) {
                return runningProcessors
            }
            runningProcessors = flowOf(
                BuyDrinkProcessor()
            )
            return runningProcessors
        }

        fun getManagementInstance(): Flow<Processor>? {
            if (managementProcessors != null) {
                return managementProcessors
            }
            managementProcessors = flowOf(
                RegisterProcessor(), PrintSpecificationProcessor(), ModifyDrinkProcessor()
            )
            return managementProcessors
        }

        fun getStatusInstance(): Processor? {
            if (statusProcessor != null) {
                return statusProcessor
            }

            statusProcessor = StatusProcessor()
            return statusProcessor
        }

    }

    suspend fun statusProcess(payload: String, writer: OutputStream): Boolean =
        getStatusInstance()?.sendResponse(payload, writer) ?: false

    suspend fun runningProcess(payload: String, writer: OutputStream) {
        if (vendorService.getVendorStatus() != VendorStatus.RUNNING) return
        getRunningInstance()?.collect { it.sendResponse(payload, writer) }
    }

    suspend fun managementProcess(payload: String, writer: OutputStream) {
        if (vendorService.getVendorStatus() != VendorStatus.MANAGEMENT) return
        getManagementInstance()?.collect { it.sendResponse(payload, writer) }
    }

    fun quitProcess(payload: String): Boolean = getStatusInstance()?.quit(payload) ?: false

}
