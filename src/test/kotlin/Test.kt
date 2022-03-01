import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import vendor1.dto.*
import vendor1.drink.DrinkName
import vendor1.vendor.VendorStatus
import java.io.*
import java.net.Socket
import java.util.*

val mapper = jacksonObjectMapper()

class VendorTest {

    @Test
    fun registerDrink() {
        // 소켓
        Socket("127.0.0.1", 9999).use {
            var result: String? = ""
            // 서버로 부터 데이터 읽기
            val read = it.getInputStream()
            // 서버에 데이터 쓰기
            val write = it.getOutputStream()
            // 관리 상태
            val management = convertCommand(VendorOperation(VendorStatus.MANAGEMENT))
            result += send(management, write, read)
            // 상품 A 등록
            val registerADrink = convertCommand(RegisterDrink(DrinkManagementType.REGISTER, DrinkName.A, 4000, 4))
            result += send(registerADrink, write, read)
            // 상품 B 등록
            val registerBDrink = convertCommand(RegisterDrink(DrinkManagementType.REGISTER, DrinkName.B, 6000, 4))
            result += send(registerBDrink, write, read)
            // 운영 상태
            val running = convertCommand(VendorOperation(VendorStatus.RUNNING))
            result += send(running, write, read)
            // 프로그램 종료
            val quit = convertCommand(VendorOperation(VendorStatus.QUIT))
            result += send(quit, write, read)
            println(result)
            write.close()
            read.close()
        }
    }

    @Test
    fun buyDrink() {
        // 소켓
        Socket("127.0.0.1", 9999).use {
            var result: String? = ""
            // 서버로 부터 데이터 읽기
            val read = it.getInputStream()
            // 서버에 데이터 쓰기
            val write = it.getOutputStream()
            for (i in 1..2) {
                // 음료수 구매
                val buyADrink = convertCommand(BuyDrink(DrinkName.A, 5000))
                result += send(buyADrink, write, read)
                val buyBDrink = convertCommand(BuyDrink(DrinkName.B, 7000))
                result += send(buyBDrink, write, read)
            }
            // 프로그램 종료
            val quit = convertCommand(VendorOperation(VendorStatus.QUIT))
            result += send(quit, write, read)
            println(result)
            write.close()
            read.close()
        }
    }


    @Test
    fun modifyDrink() {
        // 소켓
        Socket("127.0.0.1", 9999).use {
            var result: String? = ""
            // 서버로 부터 데이터 읽기
            val read = it.getInputStream()
            // 서버에 데이터 쓰기
            val write = it.getOutputStream()
            // 관리 상태
            val management = convertCommand(VendorOperation(VendorStatus.MANAGEMENT))
            result += send(management, write, read)
            // 상품 A 수정
            val modifyADrink = convertCommand(ModifyDrink(DrinkManagementType.MODIFY, DrinkName.A, 2000))
            result += send(modifyADrink, write, read)
            // 상품 B 수정
            val modifyBDrink = convertCommand(ModifyDrink(DrinkManagementType.MODIFY, DrinkName.B, 2000))
            result += send(modifyBDrink, write, read)
            // 운영 상태
            val running = convertCommand(VendorOperation(VendorStatus.RUNNING))
            result += send(running, write, read)
            // 프로그램 종료
            val quit = convertCommand(VendorOperation(VendorStatus.QUIT))
            result += send(quit, write, read)
            println(result)
            write.close()
            read.close()
        }
    }

    @Test
    fun printSpecification() {
        // 소켓
        Socket("127.0.0.1", 9999).use {
            var result: String? = ""
            // 서버로 부터 데이터 읽기
            val read = it.getInputStream()
            // 서버에 데이터 쓰기
            val write = it.getOutputStream()
            // 관리 상태
            val management = convertCommand(VendorOperation(VendorStatus.MANAGEMENT))
            result += send(management, write, read)
            // 명세서 출력
            val specification = convertCommand(PrintDrinkSpecification(DrinkManagementType.SPECIFICATION))
            result += send(specification, write, read)
            // 운영 상태
            val running = convertCommand(VendorOperation(VendorStatus.RUNNING))
            result += send(running, write, read)
            // 프로그램 종료
            val quit = convertCommand(VendorOperation(VendorStatus.QUIT))
            result += send(quit, write, read)
            println(result)
            write.close()
            read.close()
        }
    }

    private fun send(data: String, outputStream: OutputStream, inputStream: InputStream): String {
        sendCommand(data, outputStream)
        val buffer = StringBuffer()
        val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
        while (true) {
            val c = reader.read()
            if (c < 0) {
                break
            }
            if (responseEOF(c, reader)) {
                break
            }
            buffer.append(c.toChar())
        }
        return buffer.toString() + System.lineSeparator()
    }

    private fun convertCommand(any: Any): String {
        return Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(any)) + System.lineSeparator()
    }

    private fun sendCommand(data: String, outputStream: OutputStream) {
        outputStream.write(data.toByteArray(Charsets.UTF_8))
        outputStream.flush()
    }

    /**
     * 응답값 마지막 부분 체크
     * window: "\r\n\r\n"
     * linux: "\n\n"
     */
    private fun responseEOF(currentChar: Int, reader: BufferedReader): Boolean {
        val lineFeed = 10
        if (currentChar != lineFeed) {
            return false
        }
        reader.mark(26)
        if (reader.read() == lineFeed) {
            return true
        }
        if (reader.read() == lineFeed) {
            return true
        }
        reader.reset()
        return false
    }
}
