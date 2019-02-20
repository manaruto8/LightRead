package com.ma.lightread.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.ma.lightread.R
import com.ma.lightread.adapter.WeiBoReadAdapter
import com.ma.lightread.adapter.ZhiHuReadAdapter
import com.ma.lightread.app.Contants
import com.ma.lightread.model.WeiBoList
import com.ma.lightread.model.ZhiHuList
import com.ma.lightread.utils.GsonRequest
import com.ma.lightread.utils.JsoupUtils
import com.ma.lightread.utils.LogUtils
import com.ma.lightread.utils.TimeUtils
import com.ma.lightread.widget.WrapContentLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_readlist.*
import kotlinx.android.synthetic.main.fragment_weibo.*
import org.jsoup.HttpStatusException
import java.net.SocketException
import java.net.SocketTimeoutException

/**
 * Created by Ma on 2018/11/23.
 */
class WeiBoFragment:BaseFragment() {

    private val WEIBOREFRESH = 101
    private var isScrolling=false
    private var weiboList= mutableListOf<WeiBoList.WeiBoBean>()
    private var handler=object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                101 ->{
                    weiboread_recyclerView.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_weibo,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        weiboread_recyclerView.layoutManager= WrapContentLinearLayoutManager(activity)
        weiboread_recyclerView.adapter= WeiBoReadAdapter(weiboList,activity)
        weiboread_swipeLayout.setOnRefreshListener {
            weiboList.clear()
            weiboread_swipeLayout.isRefreshing=true
            loadWeiBo()

        }
        weiboread_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val index:Int=(recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if(index==weiboList.size-1){
                    loadWeiBo()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        weiboread_recyclerView.setOnTouchListener { p0, p1 ->
            weiboread_swipeLayout.isRefreshing
        }
        loadWeiBo()
    }

    private fun loadWeiBo() {
        Thread(Runnable {
            try {
                var lists= JsoupUtils.JsoupWeibo()
                if(!isScrolling) {
                    closeRefresh()
                    weiboList.addAll(lists)
                    handler.sendEmptyMessage(WEIBOREFRESH)
                }

            }catch (e: SocketException){

            }catch (e: HttpStatusException){

            }catch (e: SocketTimeoutException){

            }
        }).start()
    }

    private fun closeRefresh(){
        if(weiboread_swipeLayout.isRefreshing){
            weiboread_swipeLayout.isRefreshing=false
        }
    }
}