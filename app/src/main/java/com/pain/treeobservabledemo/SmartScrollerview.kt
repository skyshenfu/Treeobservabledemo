package com.pain.treeobservabledemo

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 * Created by zty
 *个人github地址：http://www.github.com/skyshenfu
 *日期：2017/6/12
 *版本：1.0.0
 *描述：
 */
class SmartScrollerview:ScrollView{
    interface ScrollViewListener{
        fun onNotifyChange(smartScrollerview: SmartScrollerview,x:Int,y:Int,oldX:Int,oldY:Int)

    }
    var scrollerListener:ScrollViewListener?=null
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet,
                defStyle: Int) : super(context, attrs, defStyle) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (scrollerListener!=null){
            scrollerListener!!.onNotifyChange(this,l,t,oldl,oldt)
        }
    }
}