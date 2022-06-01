package product1.employee

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.Instant

/**
 * B2B 사원 (sub-account)
 */
@Document
data class Employee(

    /**
     * 고유 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 회사 번호
     */
    val companyId: String,

    /**
     * 사원명
     */
    val name: String,

    /**
     * 직책
     */
    val position: String,

    /**
     * 이메일
     */
    val email: String,

    /**
     * 이메일 인증 여부
     */
    val emailVerified: Boolean,

    /**
     * 휴대폰 번호
     */
    val phoneNumber: String,

    /**
     * 휴대폰 번호 인증 여부
     */
    val phoneNumberVerified: Boolean,

    /**
     * 비밀번호
     */
    val password: String,

    /**
     * 조직 서비스 접근 권한
     */
    val permissions: List<EmployeePermission>,

    /**
     * 시스템 접근 권한
     */
    val roles: List<String>,

    /**
     * 생성일
     */
    @CreatedDate
    val createAt: Instant? = null,

    /**
     * 수정일
     */
    @LastModifiedDate
    val modifiedAt: Instant? = null
)
