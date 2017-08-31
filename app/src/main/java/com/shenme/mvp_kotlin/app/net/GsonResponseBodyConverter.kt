package com.shenme.mvp_kotlin.app.net

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

/**
 * Created by CANC on 2017/8/28.
 * 先一步去判断请求是否成功，
 * 根据code去判断，是就继续往下，不是直接抛出
 */
class GsonResponseBodyConverter<T> constructor(gson: Gson, type: Type) : Converter<ResponseBody, T> {
    var mGson = gson
    var mType = type
    override fun convert(value: ResponseBody?): T {
        var response = value!!.string()
        var baseResult: BaseResult = mGson.fromJson(response, BaseResult::class.java)
        if (!baseResult.error) {
            return mGson.fromJson(response, mType)
        } else {
            throw ResultException(baseResult.code, baseResult.message)
        }
    }
}