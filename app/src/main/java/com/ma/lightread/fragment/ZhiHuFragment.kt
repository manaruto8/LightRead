package com.ma.lightread.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.ma.lightread.R
import com.ma.lightread.adapter.ZhiHuReadAdapter
import com.ma.lightread.app.Contants
import com.ma.lightread.model.WeiBoList
import com.ma.lightread.model.ZhiHuList
import com.ma.lightread.utils.GsonRequest
import com.ma.lightread.utils.TimeUtils
import com.ma.lightread.widget.WrapContentLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_readlist.*
import kotlinx.android.synthetic.main.fragment_zhihu.*

/**
 * Created by Aeolus on 2018/11/23.
 */
class ZhiHuFragment:BaseFragment() {

    private var time=0
    private var zhihuTime=20130520
    private var zhihuList= mutableListOf<ZhiHuList.ZhiHuBean>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_zhihu,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zhihuTime= TimeUtils.getTime(0)
        initView()
    }

    private fun initView() {
        zhihuread_recyclerView.layoutManager= WrapContentLinearLayoutManager(activity)
        zhihuread_recyclerView.adapter= ZhiHuReadAdapter(zhihuList,activity)
        zhihuread_swipeLayout.setOnRefreshListener {
            zhihuread_swipeLayout.isRefreshing=true
            time = 0
            zhihuTime = TimeUtils.getTime(0)
            loadZhiHu()
        }
        zhihuread_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val index:Int=(recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if(index==zhihuList.size-1){
                    time-=1
                    zhihuTime=TimeUtils.getTime(time)
                    loadZhiHu()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        loadZhiHu()
    }


    private fun loadZhiHu() {
        val requestQueue= Volley.newRequestQueue(activity)
        val jsonObjectRequest= GsonRequest(Contants.ZHIHU_URL+zhihuTime,ZhiHuList::class.java,
                Response.Listener {
                    closeRefresh()
                    if(time==0) {
                        zhihuList.clear()
                    }
                    zhihuList.addAll(it.stories)
                    zhihuread_recyclerView.adapter.notifyDataSetChanged()
                },
                Response.ErrorListener {
                    closeRefresh()
                })
        requestQueue.add(jsonObjectRequest)
    }

    private fun closeRefresh(){
        if(zhihuread_swipeLayout.isRefreshing){
            zhihuread_swipeLayout.isRefreshing=false
        }
    }
}