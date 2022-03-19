import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import vendor1.account.dto.Authentication
import vendor1.drink.domain.DrinkName
import vendor1.drink.dto.BuyDrink
import vendor1.drink.dto.ModifyDrink
import vendor1.drink.dto.RegisterDrink
import vendor1.drink.dto.VendorOperation
import vendor1.drink.processor.DrinkCommand
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
            val authentication = mapper.writeValueAsString(Authentication("test", "test"))
            // 관리 상태
            val management = mapper.writeValueAsString(VendorOperation(VendorStatus.MANAGEMENT))
            result += send(convertBase64Command(DrinkCommand.STATUS.name, management, authentication), write, read)
            // 상품 A 등록
            val registerADrink = mapper.writeValueAsString(RegisterDrink(DrinkName.A, 4000, 4))
            result += send(convertBase64Command(DrinkCommand.DRINK_REGISTER.name, registerADrink), write, read)
            // 상품 B 등록
            val registerBDrink = mapper.writeValueAsString(RegisterDrink(DrinkName.B, 6000, 4))
            result += send(convertBase64Command(DrinkCommand.DRINK_REGISTER.name, registerBDrink), write, read)
            // 운영 상태
            val running = mapper.writeValueAsString(VendorOperation(VendorStatus.RUNNING))
            result += send(convertBase64Command(DrinkCommand.STATUS.name, running, authentication), write, read)
            // 프로그램 종료
            result += send(convertBase64Command(DrinkCommand.QUIT.name), write, read)
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
                val buyADrink = mapper.writeValueAsString(BuyDrink(DrinkName.A, 5000))
                result += send(convertBase64Command(DrinkCommand.DRINK_BUY.name, buyADrink), write, read)
                val buyBDrink = mapper.writeValueAsString(BuyDrink(DrinkName.B, 7000))
                result += send(convertBase64Command(DrinkCommand.DRINK_BUY.name, buyBDrink), write, read)
            }
            // 프로그램 종료
            result += send(convertBase64Command(DrinkCommand.QUIT.name), write, read)
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
            val authentication = mapper.writeValueAsString(Authentication("test", "test"))
            // 관리 상태
            val management = mapper.writeValueAsString(VendorOperation(VendorStatus.MANAGEMENT))
            result += send(convertBase64Command(DrinkCommand.STATUS.name, management, authentication), write, read)
            // 상품 A 수정
            val modifyADrink = mapper.writeValueAsString(ModifyDrink(DrinkName.A, 2000))
            result += send(convertBase64Command(DrinkCommand.DRINK_MODIFY.name, modifyADrink), write, read)
            // 상품 B 수정
            val modifyBDrink = mapper.writeValueAsString(ModifyDrink(DrinkName.B, 2000))
            result += send(convertBase64Command(DrinkCommand.DRINK_MODIFY.name, modifyBDrink), write, read)
            // 운영 상태
            val running = mapper.writeValueAsString(VendorOperation(VendorStatus.RUNNING))
            result += send(convertBase64Command(DrinkCommand.STATUS.name, running, authentication), write, read)
            // 프로그램 종료
            result += send(convertBase64Command(DrinkCommand.QUIT.name), write, read)
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
            val authentication = mapper.writeValueAsString(Authentication("test", "test"))
            // 관리 상태
            val management = mapper.writeValueAsString(VendorOperation(VendorStatus.MANAGEMENT))
            result += send(convertBase64Command(DrinkCommand.STATUS.name, management, authentication), write, read)
            // 명세서 출력
            result += send(convertBase64Command(DrinkCommand.DRINK_SPECIFICATION.name), write, read)
            // 운영 상태
            val running = mapper.writeValueAsString(VendorOperation(VendorStatus.RUNNING))
            result += send(convertBase64Command(DrinkCommand.STATUS.name, running, authentication), write, read)
            // 프로그램 종료
            result += send(convertBase64Command(DrinkCommand.QUIT.name), write, read)
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

    private fun convertBase64Command(vararg commands:String): String {
        return Base64.getEncoder().encodeToString(
            commands.joinToString(prefix = "", postfix = "", separator = " ").toByteArray()
        ) + System.lineSeparator()
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
