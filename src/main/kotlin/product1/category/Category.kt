package product1.category

import org.bson.types.ObjectId
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.data.mongodb.core.mapping.MongoId

/**
 * 상품 분류
 * 기본 진열 등록순, 추가 고도화는 나중에
 * 실세로 하위 분류 갯수 정책은 DTO로 관리한다.
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
     * 분류 타입
     */
    val displayTypes: List<MainDisplayType>,

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
        fun create(name: String, displayTypes: List<MainDisplayType>, depth: Int): Category =
            Category(
                name = name,
                isDisplay = true,
                displayTypes = displayTypes,
                depth = depth
            )
    }

}