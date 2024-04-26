package com.mis.route.e_commerce.domain.usecases.products

import com.mis.route.domain.models.product.Product
import com.mis.route.e_commerce.domain.repoistory.products_repository.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {


    suspend fun execute(categoryId: String): List<Product?> {
        return productsRepository.getProducts(categoryId)
    }
}