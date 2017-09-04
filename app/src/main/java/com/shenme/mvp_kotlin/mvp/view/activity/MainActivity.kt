package com.shenme.mvp_kotlin.mvp.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.data.response.ResponseClasses
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.longToast
import java.net.URL

/**
 * Created by CANC on 2017/9/4.
 */
class MainActivity : RxAppCompatActivity() {
    lateinit var mActivity: AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mActivity = this
        initData()
    }

    fun initData() {
        tv_android.setOnClickListener {
            var intent = Intent(mActivity, AndroidActivity::class.java)
            intent.putExtra("request_type", "android")
            startActivity(intent)
        }
        tv_ios.setOnClickListener {
            var intent = Intent(mActivity, AndroidActivity::class.java)
            intent.putExtra("request_type", "ios")
            startActivity(intent)
        }
        tv_front.setOnClickListener {
            var intent = Intent(mActivity, AndroidActivity::class.java)
            intent.putExtra("request_type", "front")
            startActivity(intent)
        }
        tv_expand.setOnClickListener {
            var intent = Intent(mActivity, AndroidActivity::class.java)
            intent.putExtra("request_type", "expand")
            startActivity(intent)
        }
        tv_video.setOnClickListener {
            var intent = Intent(mActivity, AndroidActivity::class.java)
            intent.putExtra("request_type", "video")
            startActivity(intent)
        }
        tv_Images.setOnClickListener {
            var intent = Intent(mActivity, AndroidActivity::class.java)
            intent.putExtra("request_type", "images")
            startActivity(intent)
        }
        new_request.setOnClickListener {
            async {
                var result = ForecastRequest("").execute()
                if (result != null) {
                    Log.d("aa", "ssss")
                }
                runOnUiThread { longToast("Request performed") }
            }
        }
    }
}

class Request {
    fun run() {
        var url = "https://m.baidu.com"
        val forecastJsonStr = URL(url).readText()
        Log.d("aaa", forecastJsonStr)
    }
}

class ForecastRequest(val zipCode: String) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q=shanghai"
    }

    fun execute(): ResponseClasses.ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ResponseClasses.ForecastResult::class.java)
    }
}