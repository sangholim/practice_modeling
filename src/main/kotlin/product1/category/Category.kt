package product1.category

import org.bson.types.ObjectId
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.data.mongodb.core.mapping.MongoId

/**
 * 상품 분류
 */
@Document
data class Category(
    /**
     * 고유 번호
     */
    @MongoId
    val id: ObjectId? = null,

    /**
     * 분류명
     */
    val name: String,

    /**
     * 표시 여부
     */
    val isDisplay: Boolean,

    /**
     * 분류 깊이
     * 값이 클수록 하위 분류
     */
    val depth: Int,

    ) {
    /**
     * 카테고리 트리
     */
    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'categoryId' : ?#{#self._id} }", lazy = true)
    var trees: List<CategoryTree>? = null

    companion object {
        fun create(name: String, depth: Int): Category =
            Category(
                name = name,
                isDisplay = true,
                depth = depth
            )
    }

}