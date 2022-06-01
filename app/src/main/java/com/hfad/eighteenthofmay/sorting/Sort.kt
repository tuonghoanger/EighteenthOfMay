package com.hfad.eighteenthofmay.sorting

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.delay

abstract class Sort(val listSort: RecyclerView, val adapter: ShapeAdapter, val notifyUI: Sort.OnComplete) {
    val listNum  = adapter.listNum
    val listSize = adapter.listNum.size

    open fun sort(){ }

    fun exchange(list: MutableList<Float>, i: Int, j: Int) {
        val t = list[i]
        list[i] = list[j]
        list[j] = t
    }

    suspend fun paint() {
        (0 until listSize).forEach {
            listSort.getChildAt(it).setBackgroundResource(R.color.teal_200)
            delay(5)
        }
    }

    interface OnComplete{
        fun updateUI()
    }

    fun blink(view : View) : View {
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 50 //manage the blinking time with this parameter
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        view.setBackgroundResource(R.color.yellow)
        view.startAnimation(anim)
        return view
    }

    fun stopBlink(view : View) {
        view.setBackgroundResource(R.color.blue)
        view.animation?.cancel()
    }
}