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
import com.ma.lightread.model.ZhiHuList
import kotlinx.android.synthetic.main.item_zhihuread.view.*


/**
 * Created by Aeolus on 2018/8/2.
 */
class ZhiHuReadAdapter(private val lists: List<ZhiHuList.ZhiHuBean>, context: Context) : RecyclerView.Adapter<ZhiHuReadAdapter.MyHolder>() {

    private var readContext: Context =context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_zhihuread,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(lists[position])
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun setData(zhiHuBean: ZhiHuList.ZhiHuBean){
            Glide.with(readContext).load(zhiHuBean.images[0]).placeholder(R.mipmap.loadpic).into(itemView.zhihuImg)
            itemView.zhihuTitle.text = zhiHuBean.title
            itemView.zhihuTime.text=zhiHuBean.time
            itemView.setOnClickListener{
                var toWeb=Intent(readContext,WebActivity ::class.java)
                toWeb.putExtra(Contants.ZHIHUDETAIL_ID,zhiHuBean.id.toString())
                toWeb.putExtra(Contants.TEXTTYPE,Contants.ZHIHUTYPE)
                readContext.startActivity(toWeb)
            }
        }




    }
}