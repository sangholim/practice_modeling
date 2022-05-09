package product1.category

import org.bson.types.ObjectId
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
data class CategoryTree(

    @MongoId
    val id: ObjectId? = null,

    val name: String,

    val parentId: ObjectId?,

    val categoryId: ObjectId
) {
    /**
     * 하위 카테고리들 (조회용)
     */
    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'parentId' : ?#{#self._id} }")
    var trees: List<CategoryTree>? = null

    companion object {

        fun create(category: Category, parentId: ObjectId? = null): CategoryTree =
            CategoryTree(
                name = category.name,
                categoryId = category.id!!,
                parentId = parentId,
            )
    }
}