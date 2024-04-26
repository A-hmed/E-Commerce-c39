package com.mis.route.e_commerce.data.repository.products_repository

import com.mis.route.e_commerce.domain.repoistory.categories_repository.CategoriesRepository
import com.mis.route.e_commerce.domain.repoistory.products_repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DI {

    @Binds
    abstract fun provideProductsRepo(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}