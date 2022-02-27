package vendor1

import vendor1.dto.*
import vendor1.drink.DrinkName
import vendor1.vendor.VendorStatus
import java.io.*
import java.net.Socket
import java.util.*

fun main() {
    // 소켓
    val socket = Socket("127.0.0.1", 9999)

    var read: InputStream? = null
    var write: OutputStream? = null
    try {
        // 서버로 부터 데이터 읽기
        read = socket.getInputStream()
        // 서버에 데이터 쓰기
        write = socket.getOutputStream()
        // 관리 상태
        val management = convertCommand(VendorOperation(VendorStatus.MANAGEMENT))
        sendCommand(management, write)
        receiveMessage(read)
        // 상품 A 등록
        val registerADrink = convertCommand(RegisterDrink(DrinkManagementType.REGISTER, DrinkName.A, 4000, 3))
        sendCommand(registerADrink, write)
        receiveMessage(read)
        // 상품 B 등록
        val registerBDrink = convertCommand(RegisterDrink(DrinkManagementType.REGISTER, DrinkName.B, 6000, 3))
        sendCommand(registerBDrink, write)
        receiveMessage(read)
        // 운영 상태
        val running = convertCommand(VendorOperation(VendorStatus.RUNNING))
        sendCommand(running, write)
        receiveMessage(read)
        // 음료수 구매
        val buyADrink = convertCommand(BuyDrink(DrinkName.A, 5000))
        sendCommand(buyADrink, write)
        receiveMessage(read)
        val buyBDrink = convertCommand(BuyDrink(DrinkName.B, 5000))
        sendCommand(buyBDrink, write)
        receiveMessage(read)
        // 관리 모드
        sendCommand(management, write)
        receiveMessage(read)
        // 명세서 출력
        val specification = convertCommand(PrintDrinkSpecification(DrinkManagementType.SPECIFICATION))
        sendCommand(specification, write)
        receiveMessage(read)
    } catch (e: Exception) {
        read!!.close()
        write!!.close()
        socket.close()
    } finally {
        socket.close()
        read!!.close()
        write!!.close()
    }

}

fun sendCommand(data: String, outputStream: OutputStream) {
    outputStream.write(data.toByteArray(Charsets.UTF_8))
    outputStream.flush()
}

fun convertCommand(any: Any): String {
    return Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(any)) + System.lineSeparator()
}

fun receiveMessage(inputStream: InputStream) {
    println(inputStream.bufferedReader(Charsets.UTF_8).readLine())
}
