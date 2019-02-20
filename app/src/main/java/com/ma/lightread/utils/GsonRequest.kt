package com.ma.lightread.utils

import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * Created by Aeolus on 2018/8/7.
 */
class GsonRequest<T>(url: String?, listener: Response.Listener<T>, errorListener: Response.ErrorListener?, clazz: Class<T>, method: Int): Request<T>(method, url, errorListener) {

    private var gson : Gson = Gson()
    private var listener: Response.Listener<T> = listener
    private var clazz : Class<T> = clazz


    constructor( url: String?, clazz: Class<T>, listener: Response.Listener<T>, errorListener: Response.ErrorListener?) :
            this(url,listener,errorListener,clazz,Method.GET)

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return try {
            val jsonString = String(response!!.data!!)
            LogUtils.e("$url-----$jsonString")
            Response.success(gson.fromJson(jsonString,clazz),HttpHeaderParser.parseCacheHeaders(response))
        }catch (e : UnsupportedEncodingException){
            Response.error(ParseError(e))
        }

    }

    override fun deliverResponse(response: T) {
        listener.onResponse(response)
    }

}


