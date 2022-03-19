package vendor1.processor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import vendor1.drink.processor.*
import vendor1.vendor.processor.StatusProcessor
import java.io.OutputStream

class ProcessorFactory {
    companion object {
        private var drinkProcessors: Flow<Processor>? = null
        private var statusProcessor: Processor? = null
        private var quitProcessor: Processor? = null

        fun getDrinkInstance(): Flow<Processor>? {
            if (drinkProcessors != null) {
                return drinkProcessors
            }
            drinkProcessors = flowOf(
                BuyDrinkProcessor(),
                RegisterProcessor(),
                ModifyDrinkProcessor(),
                PrintSpecificationProcessor()
            )
            return drinkProcessors
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

    suspend fun process(command: String, writer: OutputStream) {
        getDrinkInstance()?.collect { it.sendResponse(command, writer) }
    }

    suspend fun quitProcess(command: String, writer: OutputStream) {
        if (getQuitInstance()?.sendResponse(command, writer) == false) {
            return
        }
        throw RuntimeException("QUIT")
    }
}
