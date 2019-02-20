package com.ma.lightread.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ma.lightread.R

/**
 * Created by Aeolus on 2018/8/2.
 */
class SettingFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_setting,container,false)
    }
}