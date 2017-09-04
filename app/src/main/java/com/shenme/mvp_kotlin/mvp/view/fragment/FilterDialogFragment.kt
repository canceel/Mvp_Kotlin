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
import android.view.WindowManager.LayoutParams
import android.widget.LinearLayout
import com.shenme.mvp_kotlin.R


/**
 * Created by CANC on 2017/8/31.
 */
class FilterDialogFragment constructor(contentUrl: String, title: String) : DialogFragment() {
    var url: String = contentUrl
    var pageTitle = title
    lateinit var llBrowser: LinearLayout
    lateinit var llCopy: LinearLayout
    lateinit var llShare: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //放置位置
        dialog.window.setGravity(Gravity.BOTTOM)
        //设置布局
        var mView = LayoutInflater.from(activity).inflate(R.layout.dialog_fragment_filter, null)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.setWindowAnimations(R.style.anim_popup_bottombar)
        dialog.window.decorView.setPadding(0, 0, 0, 0)
        var lp = dialog.window.attributes
        lp.width = LayoutParams.MATCH_PARENT
        lp.height = LayoutParams.WRAP_CONTENT
        dialog.window.attributes = lp
        initData(mView)
        return mView
    }

    fun initData(mView: View) {
        llBrowser = mView.findViewById(R.id.ll_browser) as LinearLayout
        llCopy = mView.findViewById(R.id.ll_copy) as LinearLayout
        llShare = mView.findViewById(R.id.ll_share) as LinearLayout
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

        llShare.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TITLE, pageTitle)//标题
            intent.putExtra(Intent.EXTRA_SUBJECT, pageTitle)//标题
            intent.putExtra(Intent.EXTRA_TEXT, pageTitle)  //内容
            var content_url = Uri.parse(url)
            intent.data = content_url
            startActivity(Intent.createChooser(intent, "分享"))
        }
    }
}