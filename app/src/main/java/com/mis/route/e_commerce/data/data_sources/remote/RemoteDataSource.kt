package com.mis.route.e_commerce.data.data_sources.remote

import com.mis.route.domain.models.product.Product
import com.mis.route.e_commerce.data.models.category.Category

interface RemoteDataSource {
    suspend fun login(email: String, password: String)
    suspend fun getAllCategories(): List<Category?>
    suspend fun getProducts(categoryId: String): List<Product?>
}