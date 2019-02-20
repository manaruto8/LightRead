package com.ma.lightread

import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ma.lightread.R
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager





open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val decorView =window.decorView
//            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            decorView.systemUiVisibility = option
//            window.statusBarColor = Color.TRANSPARENT
            val window =window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val localLayoutParams = window.attributes
//            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
    }
}
