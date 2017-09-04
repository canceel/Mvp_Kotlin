package com.shenme.mvp_kotlin.mvp.contract

import com.shenme.mvp_kotlin.app.base.IView
import com.shenme.mvp_kotlin.app.data.response.Gank
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by CANC on 2017/8/29.
 */
interface AndroidContract {
    interface View : IView {

        /**
         *数据加载完毕
         */
        fun refreshComplete()

        fun setData(datas: List<Gank>)

        fun getRxpermissions(): RxPermissions
    }

    interface Presenter {
        fun getAndroidInfo(pageSize: String)
        fun getIosInfo(pageSize: String)
        fun getVideoInfo(pageSize: String)
        fun getFuliInfo(pageSize: String)
        fun getExpandInfo(pageSize: String)
        fun getFrontInfo(pageSize: String)
    }
}