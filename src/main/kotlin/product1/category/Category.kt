package product1.category

/**
 * 상품 분류
 * 기본 진열 등록순, 추가 고도화는 나중에
 * 실세로 하위 분류 갯수 정책은 DTO로 관리한다.
 */
data class Category(
    /**
     * 고유 번호
     */
    val id: String,

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

    /**
     * 하위 카테고리 id
     */
    val subCategoryIds: List<String>?,

    /**
     * 하위 카테고리들 (조회용)
     */
    val subCategories: List<Category>?

)