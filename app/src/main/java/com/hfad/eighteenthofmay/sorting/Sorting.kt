package com.hfad.eighteenthofmay.sorting

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter

interface Sorting {
    fun sort()

    fun exchange(list: MutableList<Float>, i: Int, j: Int) {
        val t = list[i]
        list[i] = list[j]
        list[j] = t
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
        view.animation.cancel()
    }
}