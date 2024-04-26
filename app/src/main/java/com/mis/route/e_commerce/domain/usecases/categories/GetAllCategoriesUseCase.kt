package com.mis.route.e_commerce.domain.usecases.categories

import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.e_commerce.domain.repoistory.auth_repository.AuthRepository
import com.mis.route.e_commerce.domain.repoistory.categories_repository.CategoriesRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private var categoriesRepository: CategoriesRepository
) {

    suspend fun execute(): List<Category?> {
        return categoriesRepository.getAllCategories()
    }
}