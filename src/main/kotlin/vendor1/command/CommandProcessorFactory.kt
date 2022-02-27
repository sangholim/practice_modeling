package vendor1.command

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import vendor1.SingletonClass
import vendor1.vendor.VendorService
import vendor1.vendor.VendorStatus
import java.io.OutputStream

class CommandProcessorFactory {
    private val vendorService: VendorService
        get() = SingletonClass.vendorService

    companion object {
        private var runningProcessors: Flow<CommandProcessor>? = null
        private var managementProcessors: Flow<CommandProcessor>? = null
        private var statusProcessor: CommandProcessor? = null

        fun getRunningInstance(): Flow<CommandProcessor>? {
            if (runningProcessors != null) {
                return runningProcessors
            }
            runningProcessors = flowOf(
                BuyDrinkCommandProcessor()
            )
            return runningProcessors
        }

        fun getManagementInstance(): Flow<CommandProcessor>? {
            if (managementProcessors != null) {
                return managementProcessors
            }
            managementProcessors = flowOf(
                RegisterCommandProcessor(), PrintSpecificationCommandProcessor()
            )
            return managementProcessors
        }

        fun getStatusInstance(): CommandProcessor? {
            if (statusProcessor != null) {
                return statusProcessor
            }

            statusProcessor = StatusCommandProcessor()
            return statusProcessor
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

    fun quitProcess(): Boolean = getStatusInstance()?.quit() ?: false

}
