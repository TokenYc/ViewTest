package com.qianfan.viewtest

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val detector = GestureDetector(this, this)
        tv_hello.setOnTouchListener { v, event ->
            detector.onTouchEvent(event)
        }

        btn_scroll.setOnClickListener {
            tv_hello.scrollBy(-100, 0)
            showInfo()
        }

        btn_anim.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.test_animation)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    showInfo()
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
            tv_hello.startAnimation(animation)
        }

        btn_obj_anim.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(tv_hello, "translationX", 0f, 100f)
            objectAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    showInfo()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
            objectAnimator.start()
        }

        btn_layout.setOnClickListener {
            val lp: ViewGroup.MarginLayoutParams = tv_hello.layoutParams as ViewGroup.MarginLayoutParams
            lp.leftMargin += 100
            tv_hello.layoutParams = lp
            btn_layout.postDelayed({ showInfo() }, 100)
        }

        btn_scroller.setOnClickListener {
            scrollView.smoothScrollBy(-100, 0)
            btn_layout.postDelayed({ showInfo() }, 200)
        }
    }

    fun showInfo() {
        tv_info.text = getXYString()
    }

    fun getXYString(): String {
        return "x:${tv_hello.x}\n" +
                "y:${tv_hello.y}"
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.d("xxx", "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.d("xxx", "onSingleTapUp")
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.d("xxx", "onDown")
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d("xxx", "onFling")
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.d("xxx", "onScroll")
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.d("xxx", "onLongPress")
    }
}