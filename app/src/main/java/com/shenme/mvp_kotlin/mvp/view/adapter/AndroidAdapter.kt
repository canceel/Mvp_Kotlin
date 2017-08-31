package com.shenme.mvp_kotlin.mvp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.data.response.Gank
import com.shenme.mvp_kotlin.app.utils.DateUtils

/**
 * Created by CANC on 2017/8/30.
 */
class AndroidAdapter(val context: Context,
                     var datas: List<Gank>,
                     var lisenter: AndroidListener) : RecyclerView.Adapter<AndroidAdapter.AndroidHolder>() {

    fun setData(datas: List<Gank>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AndroidHolder {
        var contentView = LayoutInflater.from(context).inflate(R.layout.item_android, parent, false)
        return AndroidHolder(contentView)
    }

    override fun onBindViewHolder(holder: AndroidHolder, position: Int) {
        var gank = datas[position]
        if (gank.images != null && gank.images!!.size > 0) {
            holder.imageView.visibility = View.VISIBLE
            Glide.with(context).load(gank.images!![0]).into(holder.imageView)
        } else {
            holder.imageView.visibility = View.GONE
        }
        holder.desc.text = gank.desc
        holder.author.text = "作者: " + gank.who
        holder.publishedAt.text = DateUtils().getNormalDate(gank.publishedAt!!)
        holder.source.text = gank.source
        holder.type.text = gank.type
        holder.llAndroid.setOnClickListener {
            lisenter.itemClick(gank.url!!)
        }
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas!!.size
    }


    class AndroidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contentView = itemView
        var llAndroid = contentView.findViewById(R.id.ll_android) as LinearLayout
        var imageView = contentView.findViewById(R.id.image_view) as ImageView
        var desc = contentView.findViewById(R.id.desc) as TextView
        var author = contentView.findViewById(R.id.author) as TextView
        var publishedAt = contentView.findViewById(R.id.publishedAt) as TextView
        var source = contentView.findViewById(R.id.source) as TextView
        var type = contentView.findViewById(R.id.type) as TextView

    }

    interface AndroidListener {
        fun itemClick(url: String)
    }
}