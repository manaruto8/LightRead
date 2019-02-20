package com.ma.lightread.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Aeolus on 2018/8/10.
 */
class WrapContentLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        }catch (e : IndexOutOfBoundsException){

        }
    }
}