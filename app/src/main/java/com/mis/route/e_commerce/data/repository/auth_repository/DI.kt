package com.mis.route.e_commerce.data.repository.auth_repository

import com.mis.route.e_commerce.domain.repoistory.auth_repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DI {

    @Binds
    abstract fun provideAuthRepo(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}