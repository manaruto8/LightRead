package com.ma.lightread.fragment




import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Message.obtain
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.fragment_readlist.*


/**
 * Created by Aeolus on 2018/8/1.
 */
class ReadListFragment : BaseFragment(){

    private val fragList= listOf<Fragment>(ZhiHuFragment(), WeiBoFragment())
    private val readList= listOf(R.string.readlist_zhihu,R.string.readlist_weibo)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_readlist,container,false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        readlist_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

        })
        readlist_viewPager.adapter=ReadViewPager(childFragmentManager)
        readlist_viewPager.offscreenPageLimit=2
        readlist_tabLayout.setupWithViewPager(readlist_viewPager)
    }


    inner class ReadViewPager(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        /**
         * Return the Fragment associated with a specified position.
         */
        override fun getItem(position: Int): Fragment {
            return  fragList[position]
        }

        /**
         * Return the number of views available.
         */
        override fun getCount(): Int {
            return fragList.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return resources.getString(readList[position])
        }


    }


}