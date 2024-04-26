package com.mis.route.e_commerce.domain.usecases.auth

import com.mis.route.e_commerce.domain.repoistory.auth_repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    var authRepo: AuthRepository
) {
    var email = ""
    var password = ""
    suspend fun execute(){
        authRepo.login(email, password)
    }
}