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
     * @param data 명령어
     */
    fun process(data: String): String?

    /**
     * 커맨드 입력값 받고 처리후, 클라이언트에게 응답값 전송
     * @param data 명령어
     * @param outputStream 응답값 전달용 Stream
     */
    suspend fun sendResponse(data: String, outputStream: OutputStream): Boolean {
        try {
            val result = process(data) ?: return false
            val eof = System.lineSeparator() + System.lineSeparator()
            outputStream.write((result + eof).toByteArray())
            outputStream.flush()
            return true
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }
}

inline fun <reified T : Enum<T>> validCommand(command: String, enum: Enum<T>): Boolean {
    try {
        val enumCommand = enumValueOf<T>(command)
        if (enumCommand == enum) {
            return true
        }
    } catch (e: IllegalArgumentException) {
        // debug
        println("not found command")
    }
    return false
}

inline fun <reified T> entityBinder(payload: String): T {
    try {
        return mapper.readValue(payload, T::class.java)
    } catch (e: Exception) {
        throw RuntimeException("parse-fail")
    }
}