package product1.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import product1.base.AbstractDbIntegrationTests
import product1.category.CategoryRepository

class CategoryRepositoryTests: AbstractDbIntegrationTests() {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Test
    fun test() {
        val list = categoryRepository.findAll()
        println(list.size)
    }
}