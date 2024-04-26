package com.mis.route.e_commerce.data.repository.categories_repository

import com.mis.route.e_commerce.data.repository.auth_repository.AuthRepositoryImpl
import com.mis.route.e_commerce.domain.repoistory.auth_repository.AuthRepository
import com.mis.route.e_commerce.domain.repoistory.categories_repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DI {

    @Binds
    abstract fun provideCategoriesRepo(
        categoriesRepositoryImpl: CategoriesRepositoryImpl
    ): CategoriesRepository
}