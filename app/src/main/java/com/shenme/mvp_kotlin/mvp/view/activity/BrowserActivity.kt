package com.shenme.mvp_kotlin.mvp.view.activity

import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.base.BaseActivity

/**
 * Created by CANC on 2017/8/30.
 */
class BrowserActivity : BaseActivity() {
    internal var WEB_URL = "web_url"
    var url: String? = null
    lateinit var webView: WebView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        initData()
    }

    fun initData() {
        url = intent.getStringExtra("web_url")
        webView = findViewById(R.id.web_view) as WebView
        progressBar = findViewById(R.id.progress_bar) as ProgressBar
        val webSettings = webView.settings
        /**
         * 设置WebView属性，能够执行Javascript脚本
         */
        webSettings.javaScriptEnabled = true
        // 设置可以访问文件
        webSettings.allowFileAccess = true
        // 设置可以支持缩放
        webSettings.setSupportZoom(true)
        // 设置出现缩放工具
        webSettings.builtInZoomControls = false
        webSettings.defaultFontSize = 20
        window.setFormat(PixelFormat.TRANSLUCENT)
        //
        webSettings.userAgentString = webSettings.userAgentString + R.string.app_name
        /** 加载前清除缓存  */
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.clearCache(true)
        webView.setWebViewClient(WebViewClient())
        webView.setWebViewClient(object : WebViewClient() {

            // 页面开始加载
            //TODO kotlin 使用webview在这里会奔溃，不知道为啥
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            // 页面加载完成
            override fun onPageFinished(view: WebView, url: String) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            // WebView加载的所有资源url
            override fun onLoadResource(view: WebView, url: String) {
                super.onLoadResource(view, url)

            }

        })

        webView.setWebChromeClient(object : WebChromeClient() {

            // 设置网页加载的进度条
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (progressBar != null) {
                    progressBar.setProgress(newProgress)
                }
                super.onProgressChanged(view, newProgress)
            }

            // 设置程序的Title
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
            }
        })

        webView.loadUrl(url)
    }
}