package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter

class Shell(listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort,adapter,notifyUI) {

    override fun sort() {
        val n: Int = listNum.size
        var h = 1
        while (h < n / 3) h = 3 * h + 1  // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        while (h >= 1) {
            for (i in h until n) {
                var j = i
                while (j >= h && listNum[j] < listNum[j - h]) {
                    exchange(listNum, j, j - h)
                    j -= h
                }
            }
            h /= 3
        }
    }

}