package com.shenme.mvp_kotlin.net

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by CANC on 2017/8/28.
 */
class ResponseConverterFactory private constructor(var gson: Gson) : Converter.Factory() {

    /**
     * 静态方法
     */
    companion object {
        fun create(gson: Gson = Gson()): ResponseConverterFactory {
            return ResponseConverterFactory(gson)
        }
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, Any> {
        return GsonResponseBodyConverter(gson, type)
    }


}