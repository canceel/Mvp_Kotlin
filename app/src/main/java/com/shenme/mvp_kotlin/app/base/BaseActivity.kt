package com.shenme.mvp_kotlin.app.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.wenming.swipebacklayout.SwipeBackLayout
import com.wenming.swipebacklayout.app.SwipeBackActivity

/**
 * Created by CANC on 2017/8/30.
 */
open class BaseActivity : SwipeBackActivity() {
    lateinit var mActivity: AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeBackLayout.setSwipeMode(SwipeBackLayout.FULL_SCREEN_LEFT)
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
        swipeBackLayout.setSensitivity(this, 0.3f)
        mActivity = this
    }

    fun back(view: View) {
        onBackPressed()
    }

    fun close(view: View) {
        finish()
    }
}