package com.mis.route.e_commerce.data.repository.categories_repository

import com.mis.route.e_commerce.data.data_sources.remote.RemoteDataSource
import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.e_commerce.domain.repoistory.categories_repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): CategoriesRepository {
    override suspend fun getAllCategories(): List<Category?> {
        return remoteDataSource.getAllCategories()
    }
}