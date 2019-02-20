package com.ma.lightread.model

/**
 * Created by Aeolus on 2018/8/9.
 */
class ZhiHuList {

    var date : String=""
    var stories : List<ZhiHuBean> = listOf()

    class ZhiHuBean{
        var images : List<String> = listOf()
        var title : String =""
        var text : String =""
        var url : String =""
        var time : String =""
        var source : String =""
        var id : Int=0
        var type : Int=0
    }

}