package product1.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.category.Category
import product1.category.CategoryRepository
import product1.category.MainDisplayType

class CategoryRepositoryTests: AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Test
    fun categoriesTest() {
        createCategories()
        val list = categoryRepository.findAll()
        println(list.size)
    }

    private fun createCategories() {
        // 1뎁스 카테 고리 생성
        val firstCategory = Category(null, "1뎁스", true, listOf(MainDisplayType.FIRST, MainDisplayType.CHILD, MainDisplayType.OTHER), 0, null)
        val savedFirstCategory = categoryRepository.save(firstCategory)

        // 2뎁스 카테고리 생성
        val secondCategories = listOf(
            Category(null, "2뎁스-1", true, listOf(MainDisplayType.FIRST, MainDisplayType.CHILD, MainDisplayType.OTHER), 1, null),
            Category(null, "2뎁스-2", true, listOf(MainDisplayType.FIRST, MainDisplayType.CHILD, MainDisplayType.OTHER), 1, null)
        )
        val savedSecondCategories = categoryRepository.saveAll(secondCategories)
        val secondCategoryIds = savedSecondCategories.map { it.id!! }
        // 1뎁스 카테고리 업데이트
        categoryRepository.save(savedFirstCategory.copy(subCategoryIds = secondCategoryIds))

        // 3뎁스 카테고리 생성
        val thirdCategories = listOf(
            Category(null, "3뎁스-1", true, listOf(MainDisplayType.FIRST, MainDisplayType.CHILD, MainDisplayType.OTHER), 2, null),
            Category(null, "3뎁스-2", true, listOf(MainDisplayType.FIRST, MainDisplayType.CHILD, MainDisplayType.OTHER), 2, null)
        )
        val savedThirdCategories = categoryRepository.saveAll(thirdCategories)
        val thirdCategoryIds = savedThirdCategories.map { it.id!! }
        // 2뎁스 카테고리 업데이트
        categoryRepository.saveAll(savedSecondCategories.map {it.copy(subCategoryIds = thirdCategoryIds)})
    }
}