package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter

class Bubble(listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort,adapter,notifyUI) {

    override fun sort() {
        val n = listNum.count()
        for (i in n - 1 downTo 0) {
            for (j in 0 until i) {
                if (listNum[j] > listNum[j + 1]) {
                    exchange(listNum, j, j + 1)
                }
            }
        }
    }

}