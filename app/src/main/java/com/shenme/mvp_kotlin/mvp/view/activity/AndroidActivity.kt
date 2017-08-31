package com.shenme.mvp_kotlin.mvp.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.MyApplication
import com.shenme.mvp_kotlin.app.base.RefreshActivity
import com.shenme.mvp_kotlin.app.data.response.Gank
import com.shenme.mvp_kotlin.di.component.DaggerAndroidComponent
import com.shenme.mvp_kotlin.di.module.AndroidModule
import com.shenme.mvp_kotlin.mvp.contract.AndroidContract
import com.shenme.mvp_kotlin.mvp.presenter.AndroidPresenter
import com.shenme.mvp_kotlin.mvp.view.adapter.AndroidAdapter
import kotlinx.android.synthetic.main.activity_android.*
import javax.inject.Inject

class AndroidActivity : RefreshActivity(), AndroidContract.View, AndroidAdapter.AndroidListener {


    @Inject
    lateinit var presenter: AndroidPresenter

    lateinit var adapter: AndroidAdapter
    lateinit var datas: MutableList<Gank>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        setupActivityComponent()
        initView()
    }

    override fun setupActivityComponent() {
        DaggerAndroidComponent.builder()
                .androidModule(AndroidModule(this))
                .netComponent(MyApplication()[this].netComponent)
                .build()
                .inject(this)
    }

    fun initView() {
        setRefreshInfo(refresh_layout)
        refresh_layout.isEnableAutoLoadmore = true
        datas = ArrayList()
        adapter = AndroidAdapter(mActivity, datas, this)
        var mLayoutManager = LinearLayoutManager(mActivity)
        recycler_view.layoutManager = mLayoutManager
        recycler_view.adapter = adapter
        refresh_layout.autoRefresh()
    }

    /**
     * 加载数据
     */
    override fun getData(isShowDialog: Boolean) {
        presenter.getAndroidInfo(20.toString())
    }

    override fun refreshComplete() {
        refresh_layout!!.finishLoadmore()
        refresh_layout!!.finishRefresh()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun setData(result: List<Gank>) {
        if (isRefresh) datas!!.clear() else datas.addAll(result)
        datas.addAll(result)
        adapter.setData(datas)
    }

    override fun itemClick(url: String) {
        var intent = Intent()
        intent.setClass(mActivity, BrowserActivity::class.java)
        intent.putExtra(BrowserActivity().WEB_URL, url)
        startActivity(intent)

    }

}
