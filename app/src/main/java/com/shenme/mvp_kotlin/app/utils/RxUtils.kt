package com.shenme.mvp_kotlin.app.utils

import com.shenme.mvp_kotlin.app.base.IView
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * Created by CANC on 2017/8/31.
 */
class RxUtils {
    fun <T> bindToLifecycle(view: IView): LifecycleTransformer<T> {
        if (view is RxAppCompatActivity) {
            return (view as RxAppCompatActivity).bindToLifecycle<T>()
        } else if (view is RxFragment) {
            return (view as RxFragment).bindToLifecycle<T>()
        } else {
            throw IllegalArgumentException("view isn't activity or fragment")
        }

    }
}