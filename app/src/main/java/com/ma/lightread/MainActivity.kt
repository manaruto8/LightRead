package com.ma.lightread

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.ma.lightread.fragment.ReadListFragment
import com.ma.lightread.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val fragList= listOf<Fragment>(ReadListFragment(), SettingFragment())
    private val titleList= listOf(R.string.mainlist_read,R.string.mainlist_setting)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        main_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }


            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        main_viewPager.adapter=MainViewPager(supportFragmentManager)
        main_viewPager.offscreenPageLimit=2
        main_tabLayout.setupWithViewPager(main_viewPager)
    }

    inner class MainViewPager(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
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
            return resources.getString(titleList[position])
        }

    }
}
