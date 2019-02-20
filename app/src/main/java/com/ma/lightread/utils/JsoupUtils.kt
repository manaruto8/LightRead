package com.ma.lightread.utils

import android.content.Context
import android.content.Intent
import android.text.TextUtils.split
import com.ma.lightread.WebActivity
import com.ma.lightread.app.Contants
import com.ma.lightread.model.WeiBoList
import org.jsoup.Jsoup
import org.jsoup.select.Selector.select

/**
 * Created by Aeolus on 2018/11/8.
 */
class JsoupUtils {

    companion object {

        fun  JsoupWeibo():MutableList<WeiBoList.WeiBoBean>{
            var weiboList =mutableListOf<WeiBoList.WeiBoBean>()
            var map=HashMap<String,String>()
            map["Apache"] = "6274877643104.91.1542610076640"
            map["SCF"]="AqNNmKkT-_deOfVVoaA8D6ncgc2Ne9C0EBgoSiIhF2sy_i-LJBQ9Nu4Vk_Jeo2cJN-QFejYh29DCfSPoYrwTFqA."
            map["SINAGLOBAL"]="9318748310815.455.1494237326065"
            map["SUB"]="_2AkMsrghGdcPxrABRmvAWzGLjbY5H-jyfe2GwAn7uJhMyOhgv7kgAqSVutBF-XIyJdq0Rv50pkRpLTIowYeJEzEvT"
            map["SUBP"]="0033WrSXqPxfM72wWs9jqgMF55529P9D9WFIEPNGVwXqmJvmxM70BXBb5JpV2K2E1K2RS05Ee0L5MP2Vqcv_"
            map["SUHB"]="0C1CvGx5Ii5Ef4"
            map["ULV"]="1542610076735:51:2:1:6274877643104.91.1542610076640:1541662289425"
            map["UOR"]="www.liaoxuefeng.com,widget.weibo.com,www.baidu.com"
            map["Ugrow-G0"]="169004153682ef91866609488943c77f"
            map["WBStorage"]="f44cc46b96043278|undefined"
            map["YF-Page-G0"]="d52660735d1ea4ed313e0beb68c05fc5"
            map["YF-V5-G0"]="cd5d86283b86b0d506628aedd6f8896e"
            map["_s_tentry"]="www.baidu.com"
            map["cross_origin_proto"]="SSL"
            map["login_sid_t"]="fe8dd7b8de0a07321cf10f2fc0862d57"
            map["wb_view_log"]="1366*7681"
            LogUtils.e("----")
            var document=Jsoup.connect(Contants.WEIBO_URL)
                    .userAgent(Contants.USER_AGENT)
                    .timeout(5000)
                    .cookies(map)
                    .get()
//            var element=document.select(".B_unlog")
//                    .select("div.WB_miniblog")
//                    .select("div.WB_miniblog_fb")
//                    .select("div.WB_main.clearfix")//#plc_frame
//                    .select("div.WB_frame")
//                    .select("div#plc_main")
//                    .select("div#plc_unlogin_home_main")
//                    .select("div.WB_frame_c")
//                    .select("div#pl_unlogin_home_feed")
//                    .select("div.UG_contents")
//                    .select("ul.pt_ul.clearfix")
//                    .select("div.UG_list_b")
//            LogUtils.e(element.select("div.list_des").select("h3.list_title_b").select("a").text())
            var htmlText =document.html()
                    .split("<script charset=\"utf-8\">FM.view({")[3]
                    .split("\"html\":\"")[1]
                    .split("\"})</script>")[0]
                    .replace("\\n", "")
                    .replace("\\r", "")
                    .replace("\\/", "/")
                    .replace("\\\"", "\"")

            var htmlEle=Jsoup.parse(htmlText).select("ul.pt_ul.clearfix").select("div.UG_list_b")
            //titleList=htmlEle.select("div.UG_list_b").select("h3.list_title_b").select("a").
            LogUtils.e(htmlEle.html())
            for (i in htmlEle.indices){
                var weiBoBean : WeiBoList.WeiBoBean= WeiBoList.WeiBoBean()
                weiBoBean.images=htmlEle[i].select("div.pic.W_piccut_v").select("img").attr("src")
                weiBoBean.title=htmlEle[i].select("div.list_des").select("h3.list_title_b").select("a").text()
                weiBoBean.url=htmlEle[i].select("div.list_des").select("h3.list_title_b").select("a").attr("href")
                weiBoBean.time=htmlEle[i].select("div.list_des").select("div.subinfo_box.clearfix").select("span.subinfo.S_txt2").text()
                weiBoBean.source=htmlEle[i].select("div.list_des").select("div.subinfo_box.clearfix").select("a").select("span.subinfo.S_txt2").text()
                weiboList.add(weiBoBean)
            }
            return weiboList
        }




    }


}