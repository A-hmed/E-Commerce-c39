package com.mis.route.e_commerce.data.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
class DI {

    @Provides
    fun provideSharedPrefrences(@ApplicationContext context: Context): SharedPreferences{
        return context.getSharedPreferences("My database", Context.MODE_PRIVATE)
    }
}