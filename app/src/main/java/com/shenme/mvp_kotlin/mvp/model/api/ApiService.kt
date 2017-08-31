package com.shenme.mvp_kotlin.mvp.model.api

import com.shenme.mvp_kotlin.app.data.response.ResponseGank
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by CANC on 2017/8/29.
 */
interface ApiService {
    @GET("/api/random/data/Android/{pageSize}")
    fun getAndroidInfo(@Path("pageSize") pageSize: String): Observable<ResponseGank>
}