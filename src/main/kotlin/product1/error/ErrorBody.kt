package product1.error

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.support.WebExchangeBindException
import java.time.Instant

class ErrorBody(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    @field:JsonInclude(value = JsonInclude.Include.NON_NULL)
    val message: String,
    @field:JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    val fields: List<SimpleFieldError>? = null
) {
    companion object {
        fun create(exception: WebExchangeBindException) = ErrorBody(
            status = exception.status.value(),
            message = exception.status.reasonPhrase,
            fields = exception.fieldErrors.map(FieldError::toSimpleFieldError)
        )

        fun create(exception: IllegalArgumentException) = ErrorBody(
            status = HttpStatus.BAD_REQUEST.value(),
            message = exception.message ?: HttpStatus.BAD_REQUEST.reasonPhrase,
        )

        fun create() = ErrorBody(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
        )
    }
}

fun FieldError.toSimpleFieldError() = SimpleFieldError(
    field = this.field,
    message = this.defaultMessage
)
