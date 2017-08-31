package com.shenme.mvp_kotlin.di.component

import com.shenme.mvp_kotlin.di.module.NetModule
import com.shenme.mvp_kotlin.mvp.model.api.ApiService
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by CANC on 2017/8/29.
 */
@Component(modules = arrayOf(NetModule::class))
@Singleton
interface NetComponent {
    fun apiService(): ApiService
    fun okhttpClient(): OkHttpClient
    fun retrofit(): Retrofit
}