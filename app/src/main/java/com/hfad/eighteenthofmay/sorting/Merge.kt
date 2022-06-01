package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter

class Merge(listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort, adapter, notifyUI) {

    override fun sort() {
        sort(listNum, 0, listSize)
    }

    fun sort(listNum: MutableList<Float>, lo: Int, hi: Int) {
        if (hi <= lo) return
        val mid = lo + (hi - lo) / 2
        sort(listNum, lo, mid)
        sort(listNum, mid + 1, hi)
        merge(listNum, lo, mid, hi)
    }

    fun merge(listNum: MutableList<Float>, lo: Int, mid: Int, hi: Int) {
        var i = lo
        var j = mid + 1
        val aux = listNum.toMutableList()
        for (k in lo..hi)
            if (i > mid) listNum[k] = aux[j++];
            else if (j > hi) listNum[k] = aux[i++];
            else if (aux[j] < aux[i]) listNum[k] = aux[j++];
            else listNum[k] = aux[i++];
    }
}