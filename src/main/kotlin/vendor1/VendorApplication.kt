package vendor1

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import vendor1.command.*
import vendor1.vendor.Vendor
import vendor1.vendor.VendorOperationService
import vendor1.vendor.VendorStatus
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread


object SingletonClass {
    val vendor = Vendor()
    val vendorOperationService = VendorOperationService()
    val runningProcessors = flowOf(BuyDrinkCommandProcessor())
    val managementProcessors = flowOf(
        PrintSpecificationCommandProcessor(),
        RegisterCommandProcessor()
    )
    val statusProcessor = StatusCommandProcessor()
}

val mapper = jacksonObjectMapper()
fun main() {
    val server = ServerSocket(9999)
    println("Open Server - ${server.localPort} -")
    while (true) {
        val client = server.accept()
        println("Client connected: ${client.inetAddress.hostAddress}")
        thread { ClientListener(client).connect() }
    }
}

class ClientListener(
    private val client: Socket
) {
    private val reader: InputStream = client.getInputStream()
    private val writer: OutputStream = client.getOutputStream()
    private val vendorOperationService = SingletonClass.vendorOperationService
    private val runningProcessors = SingletonClass.runningProcessors
    private val managementProcessors = SingletonClass.managementProcessors
    private val statusProcessor = SingletonClass.statusProcessor
    private var running = false

    fun connect() {
        running = true
        println("Vendor Drink Machine")
        while (running) {
            try {
                val command = read(reader)

                runBlocking {
                    if (statusProcessor.sendResponse(command, writer)) {
                        return@runBlocking
                    }

                    // CPU 연산 최적화
                    launch(Dispatchers.Default) {
                        if (vendorOperationService.getVendorStatus() == VendorStatus.RUNNING) {
                            runningProcessors.collect { it.sendResponse(command, writer) }
                        }
                        if (vendorOperationService.getVendorStatus() == VendorStatus.MANAGEMENT) {
                            managementProcessors.collect { it.sendResponse(command, writer) }
                        }
                    }
                }
                // 프로그램 종료 명령어
                if (statusProcessor.quit()) {
                    running = false
                    client.close()
                    println("${client.inetAddress.hostAddress} closed the connection")
                }
            } catch (e: Exception) {
                if (e.message?.equals("empty-command") == true) {
                    continue
                }
                e.printStackTrace()
                writer.write("unknown-error\r\n".toByteArray(Charsets.UTF_8))
                writer.flush()
            }
        }
    }

    fun read(inputStream: InputStream): String {
        val command = inputStream.bufferedReader(Charsets.UTF_8).readLine()
        if (command.isNullOrEmpty()) throw RuntimeException("empty-command")
        return String(Base64.getDecoder().decode(command))
    }
}


