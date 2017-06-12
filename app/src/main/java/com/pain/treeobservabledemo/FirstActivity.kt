package com.pain.treeobservabledemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewTreeObserver
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.RelativeLayout

/**
 * Created by zty
 *个人github地址：http://www.github.com/skyshenfu
 *日期：2017/6/12
 *版本：1.0.0
 *描述：
 */
class  FirstActivity : AppCompatActivity(),SmartScrollerview.ScrollViewListener {
    var height:Int =0
    var imageView:ImageView?=null
    var scrollerView:SmartScrollerview?=null
    var webview:WebView?=null
    var layoutview:RelativeLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initview()
    }

    private fun initview() {
        webview= findViewById(R.id.webview) as WebView?
        scrollerView= findViewById(R.id.smart_scrollerview) as SmartScrollerview?
        layoutview= findViewById(R.id.top_view) as RelativeLayout?
        imageView= findViewById(R.id.imageview) as ImageView?
        var setting:WebSettings=webview!!.settings
        setting.javaScriptEnabled=true
        webview!!.loadUrl("http://www.sina.com")
        webview!!.setWebViewClient(object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }
        })
        var ob:ViewTreeObserver=imageView!!.viewTreeObserver
        ob.addOnGlobalLayoutListener(object :ViewTreeObserver.OnGlobalLayoutListener{

            override fun onGlobalLayout() {
                imageView!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
                height=imageView!!.height
                scrollerView!!.scrollerListener=this@FirstActivity
            }
        })
    }

    override fun onNotifyChange(smartScrollerview: SmartScrollerview, x: Int, y: Int, oldX: Int, oldY: Int) {
    
        if (y<=height){
            val scale=y.toFloat()/height
            val alpha=255*scale
            layoutview!!.setBackgroundColor(Color.argb(alpha.toInt(),46,204,113))
        }
   
    }
}

