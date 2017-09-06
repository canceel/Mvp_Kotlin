package com.shenme.mvp_kotlin.mvp.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.data.response.ResponseClasses
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.net.URL

/**
 * Created by CANC on 2017/9/4.
 */
class MainActivity : RxAppCompatActivity() {
    lateinit var mActivity: AppCompatActivity
    lateinit var texta: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mActivity = this
        initData()
    }

    fun initData() {
        verticalLayout {
            /**
             * Android
             */
            button {
                text = "Android"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co1)
                onClick {
                    var intent = Intent(mActivity, AndroidActivity::class.java)
                    intent.putExtra("request_type", "android")
                    startActivity(intent)
                }
            }
            /**
             * IOS
             */
            button {
                text = "IOS"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co2)
                onClick {
                    var intent = Intent(mActivity, AndroidActivity::class.java)
                    intent.putExtra("request_type", "ios")
                    startActivity(intent)
                }
            }.lparams(width = matchParent) {
                topMargin = dip(1)
            }
            /**
             * Front
             */
            button {
                text = "Front"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co3)
                onClick {
                    var intent = Intent(mActivity, AndroidActivity::class.java)
                    intent.putExtra("request_type", "front")
                    startActivity(intent)
                }
            }.lparams(width = matchParent) {
                topMargin = dip(1)
            }
            /**
             * Expand
             */
            button {
                text = "Expand"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co4)
                onClick {
                    var intent = Intent(mActivity, AndroidActivity::class.java)
                    intent.putExtra("request_type", "expand")
                    startActivity(intent)
                }
            }.lparams(width = matchParent) {
                topMargin = dip(1)
            }
            /**
             * Video
             */
            button {
                text = "Video"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co5)
                onClick {
                    var intent = Intent(mActivity, AndroidActivity::class.java)
                    intent.putExtra("request_type", "video")
                    startActivity(intent)
                }
            }.lparams(width = matchParent) {
                topMargin = dip(1)
            }
            /**
             * Images
             */
            button {
                text = "Images"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co6)
                onClick {
                    var intent = Intent(mActivity, AndroidActivity::class.java)
                    intent.putExtra("request_type", "images")
                    startActivity(intent)
                }
            }.lparams(width = matchParent) {
                topMargin = dip(1)
            }
            /**
             * Anko New Request
             */
            button {
                text = "Anko New Request"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(mActivity, R.color.colorBackgroud)
                textColor = ContextCompat.getColor(mActivity, R.color.co7)
                onClick {
                    async {
                        var result = ForecastRequest("").execute()
                        if (result != null) {
                            var resultStr = result.city.toString()
                            texta.text = resultStr
                            Log.d("result", resultStr)
                        }
                    }
                }
            }.lparams(width = matchParent) {
                topMargin = dip(1)
            }
            scrollView {
                texta = textView().lparams(width = matchParent, height = matchParent) { topMargin = dip(1) }.lparams(height = matchParent, width = matchParent)
            }.lparams(width = matchParent, height = matchParent)
        }
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