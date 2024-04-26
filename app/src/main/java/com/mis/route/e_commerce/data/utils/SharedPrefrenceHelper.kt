package com.mis.route.e_commerce.data.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefrenceHelper @Inject constructor(val sharedPreferences: SharedPreferences) {
    fun saveToken(token: String) {
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun getToken(): String? {
       return sharedPreferences.getString("token", null)
    }
}