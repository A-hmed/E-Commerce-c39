package com.mis.route.e_commerce.data.repository.products_repository

import com.mis.route.domain.models.product.Product
import com.mis.route.e_commerce.data.data_sources.remote.RemoteDataSource
import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.e_commerce.domain.repoistory.categories_repository.CategoriesRepository
import com.mis.route.e_commerce.domain.repoistory.products_repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): ProductsRepository {
    override suspend fun getProducts(categoryId: String): List<Product?> {
        return remoteDataSource.getProducts(categoryId)
    }
}