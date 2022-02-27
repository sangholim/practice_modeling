package vendor1

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import vendor1.processor.*
import vendor1.vendor.Vendor
import vendor1.vendor.VendorService
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread


object Config {
    val vendor = Vendor()
    val vendorService = VendorService()
    val processorFactory = ProcessorFactory()
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
    private val processorFactory = Config.processorFactory
    private var running = false

    fun connect() {
        running = true
        println("Vendor Drink Machine")
        while (running) {
            try {
                val command = read(reader)

                runBlocking {
                    if (processorFactory.statusProcess(command, writer)) {
                        return@runBlocking
                    }

                    // CPU 연산 최적화
                    launch(Dispatchers.Default) {
                        processorFactory.runningProcess(command, writer)
                        processorFactory.managementProcess(command, writer)
                    }
                }
                // 프로그램 종료 명령어
                if (processorFactory.quitProcess()) {
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


