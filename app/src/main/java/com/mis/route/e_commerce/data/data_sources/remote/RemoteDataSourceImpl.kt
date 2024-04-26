package com.mis.route.e_commerce.data.data_sources.remote

import com.mis.route.domain.models.product.Product
import com.mis.route.e_commerce.data.api.WebServices
import com.mis.route.e_commerce.data.models.auth.LoginRequest
import com.mis.route.e_commerce.data.models.category.Category
import com.mis.route.e_commerce.data.utils.SharedPrefrenceHelper
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val webServices: WebServices,
    private val sharedPrefrenceHelper: SharedPrefrenceHelper
) :
    RemoteDataSource {
    override suspend fun login(email: String, password: String) {
        try {
            val authResponse = webServices.login(LoginRequest(email, password))
            sharedPrefrenceHelper.saveToken(authResponse.token!!)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllCategories(): List<Category?> {
        try {
            val categoriesResponse = webServices.getAllCategories()
            return categoriesResponse.data ?: emptyList()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getProducts(categoryId: String): List<Product?> {
        try {
            return webServices.getProducts(categoryId).data ?: emptyList()
        } catch (e: Exception) {
            throw e
        }
    }
}