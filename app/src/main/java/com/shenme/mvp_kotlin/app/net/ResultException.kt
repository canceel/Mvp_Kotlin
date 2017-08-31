package com.shenme.mvp_kotlin.app.net

/**
 * Created by CANC on 2017/8/28.
 */
class ResultException constructor(mErrorCode: Int, msg: String?) : RuntimeException() {

    private var errorCode: Int = mErrorCode
    override var message = msg
}