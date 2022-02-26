package vendor1

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import vendor1.vendor.Vendor
import vendor1.vendor.VendorOperationService
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread


object SingletonClass {
    val vendor = Vendor()
    val vendorOperationService = VendorOperationService()
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
    private var running = false

    fun connect() {
        running = true
        println("Vendor Drink Machine")
        while (running) {
            val command = read(reader)
            if (command.isNullOrEmpty()) {
                continue
            }
            val decodeCommand = String(Base64.getDecoder().decode(command))
            vendorOperationService.setVendorStatus(decodeCommand)?.let {
                writer.write((it + System.lineSeparator()).toByteArray(Charsets.UTF_8))
                writer.flush()
            }
            vendorOperationService.registerDrink(decodeCommand)?.let {
                writer.write((it + System.lineSeparator()).toByteArray(Charsets.UTF_8))
                writer.flush()
            }
            vendorOperationService.printSpecification(decodeCommand)?.let {
                writer.write((it + System.lineSeparator()).toByteArray(Charsets.UTF_8))
                writer.flush()
            }
            vendorOperationService.buyDrink(decodeCommand)?.let {
                writer.write((it + System.lineSeparator()).toByteArray(Charsets.UTF_8))
                writer.flush()
            }
            writer.write("test${System.lineSeparator()}".toByteArray(Charsets.UTF_8))
            writer.flush()
            if (vendorOperationService.shutdown()) {
                running = false
                client.close()
                println("${client.inetAddress.hostAddress} closed the connection")
                break
            }

        }
    }

    private fun read(inputStream: InputStream) = inputStream.bufferedReader(Charsets.UTF_8).readLine()
}


