package com.shenme.mvp_kotlin.app.base

import android.content.Intent

/**
 * Created by CANC on 2017/8/29.
 */
interface IView {

    /**
     * Dagger2初始化
     */
    fun setupActivityComponent()

    /**
     * 显示加载
     */
    fun showLoading()

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 显示信息
     */
    fun showMessage(message: String)

    /**
     * 跳转
     */
    fun launchActivity(intent: Intent)
}