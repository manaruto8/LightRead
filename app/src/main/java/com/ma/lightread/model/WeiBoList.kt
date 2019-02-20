package com.ma.lightread.model

/**
 * Created by Aeolus on 2018/11/8.
 */
class WeiBoList {

    var date : String=""
    var stories : List<WeiBoBean> = listOf()

    class WeiBoBean{
        var images : String =""
        var title : String =""
        var text : String =""
        var url : String =""
        var time : String =""
        var source : String =""
        var id : Int=0
        var type : Int=0
    }
}