package com.ma.lightread.utils

import android.util.Log

/**
 * Created by Aeolus on 2018/8/10.
 */
class LogUtils {



    companion object {

        private const val TAG:String="abc"

        fun e(msg : String){
            Log.e(TAG,msg)
        }

    }
}