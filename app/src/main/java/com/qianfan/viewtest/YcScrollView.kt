package com.qianfan.viewtest

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.Scroller

class YcScrollView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var scroller: Scroller? = null

    fun smoothScrollBy(dx: Int, dy: Int) {
        scroller = Scroller(context)
        scroller?.startScroll(scrollX, scrollY, dx, dy)
        invalidate()
    }

    override fun computeScroll() {
        if (scroller!=null) {
            if (scroller?.computeScrollOffset()!!) {
                scrollTo(scroller?.currX!!, scroller?.currY!!)
                postInvalidate()
            }
        }
    }
}