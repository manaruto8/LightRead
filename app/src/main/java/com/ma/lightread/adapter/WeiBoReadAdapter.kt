package com.ma.lightread.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ma.lightread.R
import com.ma.lightread.WebActivity
import com.ma.lightread.app.Contants
import com.ma.lightread.model.WeiBoList
import kotlinx.android.synthetic.main.item_weiboread.view.*
import kotlinx.android.synthetic.main.item_zhihuread.view.*

/**
 * Created by Aeolus on 2018/11/22.
 */
class WeiBoReadAdapter (private val lists: List<WeiBoList.WeiBoBean>, context: Context) : RecyclerView.Adapter<WeiBoReadAdapter.MyHolder>() {

    private var readContext: Context =context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_weiboread,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(lists[position])
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(weiBoBean: WeiBoList.WeiBoBean) {
            Glide.with(readContext).load(weiBoBean.images)
                    .placeholder(R.mipmap.loadpic)
                    .into(itemView.weiboImg)
            itemView.weiboTitle.text = weiBoBean.title
            itemView.weiboText.text = weiBoBean.source
            itemView.setOnClickListener {
                var toWeb = Intent(readContext, WebActivity::class.java)
                toWeb.putExtra(Contants.ZHIHUDETAIL_ID, weiBoBean.url)
                toWeb.putExtra(Contants.TEXTTYPE, Contants.WEIBOTYPE)
                readContext.startActivity(toWeb)
            }
        }


    }

}
