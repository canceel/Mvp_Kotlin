package com.shenme.mvp_kotlin.mvp.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.MyApplication
import com.shenme.mvp_kotlin.app.base.RefreshActivity
import com.shenme.mvp_kotlin.app.data.response.Gank
import com.shenme.mvp_kotlin.app.utils.StatusBarUtil
import com.shenme.mvp_kotlin.di.component.DaggerAndroidComponent
import com.shenme.mvp_kotlin.di.module.AndroidModule
import com.shenme.mvp_kotlin.mvp.contract.AndroidContract
import com.shenme.mvp_kotlin.mvp.presenter.AndroidPresenter
import com.shenme.mvp_kotlin.mvp.view.adapter.AndroidAdapter
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_android.*
import javax.inject.Inject

open class AndroidActivity : RefreshActivity(), AndroidContract.View, AndroidAdapter.AndroidListener {

    open var REQUEST_TYPE: String = "request_type"
    var requestType: String? = null
    lateinit var rxPermission: RxPermissions

    @Inject
    lateinit var presenter: AndroidPresenter

    lateinit var adapter: AndroidAdapter
    lateinit var datas: MutableList<Gank>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        setupActivityComponent()
        presenter.getLocationPermission()
        initView()
    }

    override fun setupActivityComponent() {
        rxPermission = RxPermissions(this)
        DaggerAndroidComponent.builder()
                .androidModule(AndroidModule(this))
                .netComponent(MyApplication()[this].netComponent)
                .build()
                .inject(this)
    }

    fun initView() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
        requestType = intent.getStringExtra(REQUEST_TYPE)
        when (requestType) {
            "android" -> {
                toolbar.title = "Android Random 20..."
            }
            "ios" -> {
                toolbar.title = "IOS Random 20..."
            }
            "video" -> {
                toolbar.title = "VIdeo Random 20..."
            }
            "images" -> {
                toolbar.title = "Images Random 20..."
            }
            "expand" -> {
                toolbar.title = "拓展资源 Random 20..."
            }
            "front" -> {
                toolbar.title = "前端 Random 20..."
            }
        }
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, recycler_view)
        StatusBarUtil.setPaddingSmart(this, toolbar)
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.blurview))
        StatusBarUtil.setMargin(this, findViewById(R.id.classicsheader_view))

        setRefreshInfo(refresh_layout)
        refresh_layout.isEnableAutoLoadmore = false
        refresh_layout.isEnableLoadmore = false
        datas = ArrayList()
        adapter = AndroidAdapter(mActivity, datas, requestType, this)
        var mLayoutManager = LinearLayoutManager(mActivity)
        recycler_view.layoutManager = mLayoutManager
        recycler_view.adapter = adapter
        refresh_layout.autoRefresh()
    }

    /**
     * 加载数据
     */
    override fun getData(isShowDialog: Boolean) {
        when (requestType) {
            "android" -> {
                presenter.getAndroidInfo(20.toString())
            }
            "ios" -> {
                presenter.getIosInfo(20.toString())
            }
            "video" -> {
                presenter.getVideoInfo(20.toString())
            }
            "images" -> {
                presenter.getFuliInfo(20.toString())
            }
            "expand" -> {
                presenter.getExpandInfo(20.toString())
            }
            "front" -> {
                presenter.getFrontInfo(20.toString())
            }
            else -> {
                refresh_layout.finishRefresh()
            }
        }
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
        intent.setClass(mActivity, H5Activity::class.java)
        intent.putExtra(BrowserActivity().WEB_URL, url)
        startActivity(intent)
    }

    override fun getRxpermissions(): RxPermissions {
        return rxPermission
    }

}
