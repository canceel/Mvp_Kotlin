package com.shenme.mvp_kotlin.mvp.view.activity

import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_browser.*

/**
 * Created by CANC on 2017/8/30.
 */
class BrowserActivity : BaseActivity() {
    internal var WEB_URL = "web_url"
    var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        initData()
    }

    fun initData() {
        url = intent.getStringExtra(WEB_URL);
        var webSettings = web_view.settings
        /**
         * 设置WebView属性，能够执行Javascript脚本
         */
        webSettings.javaScriptEnabled = true
        // 设置可以访问文件
        webSettings.allowFileAccess = true
        // 设置可以支持缩放
        webSettings.setSupportZoom(true)
        // 设置默认缩放方式尺寸是far
        webSettings.defaultZoom = WebSettings.ZoomDensity.MEDIUM
        // 设置出现缩放工具
        webSettings.builtInZoomControls = false
        webSettings.defaultFontSize = 20
        window.setFormat(PixelFormat.TRANSLUCENT)
        //
        webSettings.userAgentString = webSettings.userAgentString + R.string.app_name
        /** 加载前清除缓存 */
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        web_view.clearCache(true)
        // 设置WebViewClient
        web_view.setWebViewClient(object : WebViewClient() {

            // 页面开始加载
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
                progress_bar.visibility = View.VISIBLE
            }

            // 页面加载完成
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progress_bar.visibility = View.GONE
            }

            // WebView加载的所有资源url
            override fun onLoadResource(view: WebView, url: String) {
                super.onLoadResource(view, url)

            }

        })

        // 设置WebChromeClient
        web_view.setWebChromeClient(object : WebChromeClient() {

            // 设置网页加载的进度条
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (progress_bar != null) {
                    progress_bar.progress = newProgress
                }
            }

            // 设置程序的Title
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
            }
        })

        web_view.loadUrl(url)

    }
}