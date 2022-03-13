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
        private var quitProcessor: Processor? = null

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
                RegisterProcessor(),
                ModifyDrinkProcessor(),
                PrintSpecificationProcessor()
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

        fun getQuitInstance(): Processor? {
            if (quitProcessor != null) {
                return quitProcessor
            }
            quitProcessor = QuitProcessor()
            return quitProcessor
        }
    }

    suspend fun statusProcess(command: String, writer: OutputStream): Boolean =
        getStatusInstance()?.sendResponse(command, writer) ?: false

    suspend fun runningProcess(command: String, writer: OutputStream) {
        if (vendorService.getVendorStatus() != VendorStatus.RUNNING) return
        getRunningInstance()?.collect { it.sendResponse(command, writer) }
    }

    suspend fun managementProcess(command: String, writer: OutputStream) {
        if (vendorService.getVendorStatus() != VendorStatus.MANAGEMENT) return
        getManagementInstance()?.collect { it.sendResponse(command, writer) }
    }

    suspend fun quitProcess(command: String, writer: OutputStream) {
        if (getQuitInstance()?.sendResponse(command, writer) == false) {
            return
        }
        throw RuntimeException("QUIT")
    }
}
