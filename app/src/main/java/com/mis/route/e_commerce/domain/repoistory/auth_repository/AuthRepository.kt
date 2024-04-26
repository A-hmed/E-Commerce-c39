package com.mis.route.e_commerce.domain.repoistory.auth_repository

interface AuthRepository {

   suspend fun login(email: String, password: String)
}