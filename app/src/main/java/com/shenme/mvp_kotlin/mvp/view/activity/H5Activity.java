package com.shenme.mvp_kotlin.mvp.view.activity;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shenme.mvp_kotlin.R;
import com.shenme.mvp_kotlin.app.base.BaseActivity;
import com.shenme.mvp_kotlin.mvp.view.fragment.FilterDialogFragment;

import org.jetbrains.annotations.Nullable;

/**
 * Created by CANC on 2017/8/31.
 */

public class H5Activity extends BaseActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private ImageView ivMore;
    private String contentUrl;

    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        initData();
    }

    private void initData() {
        contentUrl = getIntent().getStringExtra("web_url");
        webView = (WebView) findViewById(R.id.web_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivMore = (ImageView) findViewById(R.id.iv_more);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = fragmentManager.findFragmentByTag("dialogFragment");
                if (fragment != null) {
                    fragmentTransaction.remove(fragment);
                }
                FilterDialogFragment dialogFragment = new FilterDialogFragment(contentUrl);
                dialogFragment.show(fragmentManager, "dialogFragment");//显示一个Fragment并且给该Fragment添加一个Tag，可通过findFragmentByTag找到该Fragment
            }
        });

        WebSettings ws = webView.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        // 保存表单数据
        ws.setSaveFormData(true);

        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);

        // 启动应用缓存
        ws.setAppCacheEnabled(true);
        // 设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);

//        setDefaultZoom
        // This method was deprecated in API level 19. api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(true);
        //缩放比例 1
        webView.setInitialScale(1);

        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);

        //  页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否支持多个窗口。
        ws.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

//        This enum was deprecated in API level 14. Use setTextZoom(int) and getTextZoom() instead.
        /** 设置字体默认缩放大小（默认总体为16sp，默认缩放比例为110）*/
//        int defaultTextZoom = SPUtils.getInt("UserInfo", "default_textZoom", 110);
//        ws.setTextZoom(defaultTextZoom);
        webView.clearCache(true);

        webView.setWebViewClient(new WebViewClient() {

            // 页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(url)) {
                    contentUrl = url;
                }
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);

                super.onPageFinished(view, url);
            }

            // WebView加载的所有资源url
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            // 设置网页加载的进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            // 设置程序的Title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
                super.onReceivedTitle(view, title);
            }
        });


        webView.loadUrl(contentUrl);
//        webView.loadDataWithBaseURL(null, getNewContent(""), "text/html", "utf-8", null);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && webView.canGoBack()) { // 表示按返回键
                        webView.goBack(); // 后退
                        return true; // 已处理
                    }
                }
                return false;
            }
        });

    }


    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
