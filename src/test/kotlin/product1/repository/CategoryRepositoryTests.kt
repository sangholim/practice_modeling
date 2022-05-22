package product1.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.category.*

@ExperimentalCoroutinesApi
class CategoryRepositoryTests : AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var categoryTreeRepository: CategoryTreeRepository

    @BeforeEach
    fun setUp() = runTest {
        categoryRepository.deleteAll()
        categoryTreeRepository.deleteAll()
    }

    @Test
    fun categoriesTest()= runTest {
        createCategories()
        val trees = categoryRepository.findByDepth(0).map { it.trees!! }
        assert(trees.count() == 5)
    }

    /**
     * 계층형으로 상품 분류 리스트 가져오기
     */
    private suspend fun createCategories() {
        // 1뎁스 카테 고리 생성
        val firstCategory =
            Category.create("뎁스", 0)

        val savedFirstCategoryTree = categoryRepository.save(firstCategory).let { category ->
            categoryTreeRepository.save(CategoryTree.create(category))
        }
        // 2뎁스 카테고리 생성
        val secondCategories = listOf(
            Category.create("2뎁스-1", 1),
            Category.create("2뎁스-2", 1),
        )
        val savedSecondCategories = categoryRepository.saveAll(secondCategories).map { category ->
            categoryTreeRepository.save(CategoryTree.create(category, savedFirstCategoryTree.id))
        }.toList()

        // 3뎁스 카테고리 생성
        val thirdCategories = listOf(
            Category.create("3뎁스-1", 2),
            Category.create("3뎁스-2", 2),
        )
        categoryRepository.saveAll(thirdCategories).map { category ->
            categoryTreeRepository.save(CategoryTree.create(category, savedSecondCategories[0].id))
        }
    }
}