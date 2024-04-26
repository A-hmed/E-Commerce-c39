package com.mis.route.e_commerce.domain.repoistory.products_repository

import com.mis.route.domain.models.product.Product

interface ProductsRepository {
    suspend fun getProducts(categoryId: String): List<Product?>
}