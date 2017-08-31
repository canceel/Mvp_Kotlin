package com.shenme.mvp_kotlin.mvp.contract

import com.shenme.mvp_kotlin.app.base.IView
import com.shenme.mvp_kotlin.app.data.response.Gank

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
    }

    interface Presenter {
        fun getAndroidInfo(pageSize: String)
    }
}