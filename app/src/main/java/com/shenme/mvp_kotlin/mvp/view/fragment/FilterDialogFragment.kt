package com.shenme.mvp_kotlin.mvp.view.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.LinearLayout
import com.shenme.mvp_kotlin.R


/**
 * Created by CANC on 2017/8/31.
 */
class FilterDialogFragment constructor(contentUrl: String) : DialogFragment() {
    var url: String = contentUrl
    lateinit var llBrowser: LinearLayout
    lateinit var llCopy: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //放置位置
        dialog.window.setGravity(Gravity.LEFT)
        dialog.window.setGravity(Gravity.BOTTOM)
        //设置布局
        var mView = LayoutInflater.from(activity).inflate(R.layout.dialog_fragment_filter, null)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window.setWindowAnimations(R.style.anim_popup_bottombar)

        initData(mView)
        return mView
    }

    fun initData(mView: View) {
        llBrowser = mView.findViewById(R.id.ll_browser) as LinearLayout
        llCopy = mView.findViewById(R.id.ll_copy) as LinearLayout
        llBrowser.setOnClickListener {
            var intent = Intent()
            intent.action = "android.intent.action.VIEW"
            var content_url = Uri.parse(url)
            intent.data = content_url
            startActivity(intent)
            dismiss()
        }

        llCopy.setOnClickListener {
            //获取剪贴板管理器：
            var cm = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            // 创建普通字符型ClipData
            val mClipData = ClipData.newPlainText("Label", url)
            // 将ClipData内容放到系统剪贴板里。
            cm.primaryClip = mClipData
            dismiss()
        }
    }
}