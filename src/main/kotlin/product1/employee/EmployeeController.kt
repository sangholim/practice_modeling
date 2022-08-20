package product1.employee

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/companies/{companyId}"])
class EmployeeController(
    private val employeeFacadeService: EmployeeFacadeService
) {

    @PostMapping(value = ["/employees"])
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun createEmployee(@PathVariable companyId: ObjectId, @Valid @RequestBody payload: EmployeePayload) {
        employeeFacadeService.createEmployee(companyId, payload)
    }
}