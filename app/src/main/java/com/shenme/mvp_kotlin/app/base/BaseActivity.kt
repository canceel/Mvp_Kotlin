package com.shenme.mvp_kotlin.app.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by CANC on 2017/8/30.
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var mActivity: AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
    }
}