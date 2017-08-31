package com.shenme.mvp_kotlin.app.data.response

import com.shenme.mvp_kotlin.net.BaseResult

/**
 * Created by CANC on 2017/8/30.
 */
class ResponseGank : BaseResult() {
    var results: List<Gank>? = null
}