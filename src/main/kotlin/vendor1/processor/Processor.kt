package vendor1.processor

import vendor1.Config
import vendor1.mapper
import vendor1.vendor.VendorService
import java.io.OutputStream

interface Processor {
    val vendorService: VendorService
        get() = Config.vendorService

    /**
     * 커맨드를 처리후 결과값을 받음
     * @param command 명령어
     */
    fun process(command: String): String?

    /**
     * 커맨드 입력값 받고 처리후, 클라이언트에게 응답값 전송
     * @param command 명령어
     * @param outputStream 응답값 전달용 Stream
     */
    suspend fun sendResponse(command: String, outputStream: OutputStream): Boolean {
        try {
            val result = process(command) ?: return false
            val eof = System.lineSeparator() + System.lineSeparator()
            outputStream.write((result + eof).toByteArray())
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }
}

inline fun <reified T> entityBinder(payload: String): T {
    try {
        return mapper.readValue(payload, T::class.java)
    } catch (e: Exception) {
        throw RuntimeException("parse-fail")
    }
}