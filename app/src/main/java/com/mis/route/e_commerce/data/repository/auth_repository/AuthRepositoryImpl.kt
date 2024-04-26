package com.mis.route.e_commerce.data.repository.auth_repository

import com.mis.route.e_commerce.data.data_sources.remote.RemoteDataSource
import com.mis.route.e_commerce.domain.repoistory.auth_repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private var remoteDataSource: RemoteDataSource
): AuthRepository {
    override suspend fun login(email: String, password: String) {
        remoteDataSource.login(email, password)
    }
}