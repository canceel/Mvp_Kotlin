package com.shenme.mvp_kotlin.app.base

import android.os.Bundle
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * Created by CANC on 2017/8/30.
 */
abstract class RefreshActivity : BaseActivity() {
    var pageSize: Int = 10
    var page: Int = 1
    var isRefresh: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setRefreshInfo(refreshLayout: SmartRefreshLayout) {
        refreshLayout.setOnRefreshListener {
            isRefresh = true
            page = 1
            getData(false)
        }
        refreshLayout.setOnLoadmoreListener {
            isRefresh = false
            page++
            getData(false)
        }

    }

    abstract fun getData(isShowDialog: Boolean)


}