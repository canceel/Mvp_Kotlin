package com.shenme.mvp_kotlin.app.net

/**
 * Created by CANC on 2017/8/28.
 */
open class BaseResult {
    var error: Boolean = false
    var code: Int = 0
    var message: String? = null
}