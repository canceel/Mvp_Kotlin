package com.shenme.mvp_kotlin.app.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by CANC on 2017/8/30.
 */
open class BaseActivity : RxAppCompatActivity() {
    lateinit var mActivity: AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
    }

    fun back(view: View) {
        onBackPressed()
    }

    fun close(view: View) {
        finish()
    }
}