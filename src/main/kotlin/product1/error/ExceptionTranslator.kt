package product1.error

import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class ExceptionTranslator {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<ErrorBody> =
        ResponseEntity.badRequest().body(ErrorBody.create(e))

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorBody> =
        ResponseEntity.badRequest().body(ErrorBody.create(e))

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorBody> =
        ResponseEntity.internalServerError().body(ErrorBody.create())
}