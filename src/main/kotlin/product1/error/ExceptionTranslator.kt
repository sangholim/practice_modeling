package product1.error

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ExceptionTranslator {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<ErrorBody> {
        logger.error(e.stackTraceToString())
        return ResponseEntity.status(e.status).body(ErrorBody.create(e))
    }

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<ErrorBody> {
        logger.error(e.stackTraceToString())
        return ResponseEntity.badRequest().body(ErrorBody.create(e))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorBody> {
        logger.error(e.stackTraceToString())
        return ResponseEntity.badRequest().body(ErrorBody.create(e))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorBody> {
        logger.error(e.stackTraceToString())
        return ResponseEntity.internalServerError().body(ErrorBody.create())
    }
}