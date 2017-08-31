package com.shenme.mvp_kotlin.di.module

import com.shenme.mvp_kotlin.BuildConfig
import com.shenme.mvp_kotlin.mvp.model.api.ApiService
import com.shenme.mvp_kotlin.mvp.model.api.Constants
import com.shenme.mvp_kotlin.app.net.ResponseConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by CANC on 2017/8/28.
 */
@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        var loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        var okhttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        return okhttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(Constants().BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}