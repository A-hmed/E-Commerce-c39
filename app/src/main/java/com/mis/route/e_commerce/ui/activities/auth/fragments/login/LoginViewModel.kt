package com.mis.route.e_commerce.ui.activities.auth.fragments.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.e_commerce.domain.usecases.auth.LoginUseCase
import com.mis.route.e_commerce.ui.base.BaseViewModel
import com.mis.route.e_commerce.ui.model.ViewMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private var loginUseCase: LoginUseCase) : BaseViewModel() {
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")

    var emailErrorLiveData = MutableLiveData<String>()
    var passwordErrorLiveData = MutableLiveData<String>()
    var events = MutableLiveData<LoginEvents?>()

    fun login() {
        viewModelScope.launch {
            try {
                Log.e("login", "Signing in ")
                isLoadingLiveData.value = true
                loginUseCase.email = emailLiveData.value!!
                loginUseCase.password = passwordLiveData.value!!
                loginUseCase.execute()
                isLoadingLiveData.value = false
                events.value = LoginEvents.SuccessfulLogin
            } catch (e: Exception) {
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error", message = "${e.message}",
                    posButtonTitle = "ok",
                )
            }

        }
    }
}

