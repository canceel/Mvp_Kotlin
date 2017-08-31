package com.shenme.mvp_kotlin.di.component

import com.shenme.mvp_kotlin.di.module.AndroidModule
import com.shenme.mvp_kotlin.di.scopes.UserScope
import com.shenme.mvp_kotlin.mvp.view.activity.AndroidActivity
import dagger.Component

/**
 * Created by CANC on 2017/8/29.
 */
@UserScope
@Component(modules = arrayOf(AndroidModule::class), dependencies = arrayOf(NetComponent::class))
interface AndroidComponent {
    fun inject(activity: AndroidActivity)
}