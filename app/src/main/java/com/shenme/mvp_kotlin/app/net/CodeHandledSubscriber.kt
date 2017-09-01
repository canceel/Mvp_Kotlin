package com.shenme.mvp_kotlin.app.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by CANC on 2017/8/29.
 */
abstract class CodeHandledSubscriber<Any> : Observer<Any> {
    override fun onSubscribe(d: Disposable?) {
    }

    override fun onError(e: Throwable?) {
        var baseResult = BaseResult()
        baseResult.message = e!!.message
        onNextError(baseResult)
        onComplete()
    }

    override fun onNext(value: Any) {
        if (value is BaseResult) {
            var baseResult: BaseResult = value
            if (!baseResult.error) {
                onNextSuccess(value)
            } else {
                onNextError(baseResult)
            }
        }
        onComplete()
    }

    abstract fun onNextSuccess(result: Any)
    abstract fun onNextError(baseResult: BaseResult?)
}