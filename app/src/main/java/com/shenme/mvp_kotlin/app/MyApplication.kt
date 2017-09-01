package com.shenme.mvp_kotlin.app

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.shenme.mvp_kotlin.di.component.DaggerNetComponent
import com.shenme.mvp_kotlin.di.component.NetComponent
import com.shenme.mvp_kotlin.di.module.NetModule


/**
 * Created by CANC on 2017/8/29.
 */
class MyApplication : MultiDexApplication() {
    var instance: Context? = null
    var netComponent: NetComponent? = null

    operator fun get(context: Context): MyApplication {
        return context.applicationContext as MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
        initNetComponent()
        initSmart()

    }

    fun initNetComponent() {
        netComponent = DaggerNetComponent.builder()
                .netModule(NetModule())
                .build()
    }

    fun initSmart() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(DefaultRefreshHeaderCreater { context, _ ->
            //指定为经典Header，默认是 贝塞尔雷达Header
            ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate)
        })
        //指定为经典Footer，默认是 BallPulseFooter
        SmartRefreshLayout.setDefaultRefreshFooterCreater { context, _ ->
            ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale)
        }
    }
}