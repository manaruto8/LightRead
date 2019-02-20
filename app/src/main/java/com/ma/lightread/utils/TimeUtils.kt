package com.ma.lightread.utils

import java.util.*

/**
 * Created by Aeolus on 2018/8/8.
 */
class TimeUtils {

    companion object {
        fun getTime(i: Int): Int{
            val calendar=java.util.Calendar.getInstance()
            calendar.add( Calendar. DATE, i)
            val year=calendar.get(java.util.Calendar.YEAR)
            val month=calendar.get(java.util.Calendar.MONTH)+1
            val day=calendar.get(java.util.Calendar.DAY_OF_MONTH)+1
            return year*10000+month*100+day
        }
    }

}