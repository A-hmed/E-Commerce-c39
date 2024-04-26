package com.mis.route.e_commerce.domain.repoistory.categories_repository

import com.mis.route.e_commerce.data.models.category.Category

interface CategoriesRepository {
    suspend fun getAllCategories(): List<Category?>
}