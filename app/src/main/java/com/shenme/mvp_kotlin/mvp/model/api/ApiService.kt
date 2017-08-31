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

    @GET("/api/random/data/iOS/{pageSize}")
    fun getIosInfo(@Path("pageSize") pageSize: String): Observable<ResponseGank>

    @GET("/api/random/data/休息视频/{pageSize}")
    fun getVideoInfo(@Path("pageSize") pageSize: String): Observable<ResponseGank>

    @GET("/api/random/data/福利/{pageSize}")
    fun getFuliInfo(@Path("pageSize") pageSize: String): Observable<ResponseGank>

    @GET("/api/random/data/拓展资源/{pageSize}")
    fun getExpandInfo(@Path("pageSize") pageSize: String): Observable<ResponseGank>

    @GET("/api/random/data/前端/{pageSize}")
    fun getFrontInfo(@Path("pageSize") pageSize: String): Observable<ResponseGank>
}