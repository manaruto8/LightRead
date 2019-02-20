package com.ma.lightread

import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.http.SslError
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.ma.lightread.app.Contants
import com.ma.lightread.model.ZhiHuDetail
import com.ma.lightread.model.ZhiHuList
import com.ma.lightread.utils.GsonRequest
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.fragment_readlist.*

class WebActivity : BaseActivity() {


    private val META_TAG ="<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>"
    private var LINK_TAG ="<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />"
    private var HTML = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%SectionsContent\"/>"


    private var id:String=""
    private var zhiHuDetail : ZhiHuDetail?=null
    private var type:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        id=intent.getStringExtra(Contants.ZHIHUDETAIL_ID)
        type=intent.getIntExtra(Contants.TEXTTYPE,0)
        initView()
        if(type==Contants.ZHIHUTYPE) {
            loadZhiHuText()
        }else if(type==Contants.WEIBOTYPE) {
            loadWeiBoText()
        }else if(type==2) {
            loadZhiHuText()
        }else if(type==3) {
            loadZhiHuText()
        }
    }

    private fun initView() {
        val webSettings= web_webView.settings
        webSettings.javaScriptEnabled = true
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = false //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件

        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"//设置编码格式


        web_webView.isVerticalScrollBarEnabled=false
        web_webView.isHorizontalScrollBarEnabled=false
        web_webView.webViewClient=MyWebViewClient()
    }

    private fun loadZhiHuText() {
        val requestQueue= Volley.newRequestQueue(this)
        val jsonObjectRequest= GsonRequest(Contants.ZHIHUDETAIL_URL+id, ZhiHuDetail::class.java,
                Response.Listener {
                    zhiHuDetail=it
                    LINK_TAG="<link rel=\"stylesheet\" type=\"text/css\" href=\""+it.css[0]+"\" />"
                    HTML= "<html><header>" +META_TAG+LINK_TAG+ "<style> div.headline{ display: none }</style></header><body>" + it.body + "</body></html>"
                    web_webView.loadDataWithBaseURL(it.css[0],HTML,"text/html; charset=UTF-8", null,null)
                },
                Response.ErrorListener {

                })
        requestQueue.add(jsonObjectRequest)
    }

    private fun loadWeiBoText() {
        web_webView.loadUrl(id)
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            //imgReset()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            var  sslErrorHandler : SslErrorHandler?=handler
            val builder : AlertDialog.Builder=AlertDialog.Builder(this@WebActivity)
            builder.setMessage("SSL证书验证失败")
            builder.setPositiveButton("继续", DialogInterface.OnClickListener { dialogInterface, i -> sslErrorHandler?.proceed() })
            builder.setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i -> sslErrorHandler?.cancel() })
            builder.create().show()
        }


    }
}
