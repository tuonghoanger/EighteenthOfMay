package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Shell(listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort,adapter,notifyUI) {
    val speed: Long = if (listSize < 11) 200 else if (listSize in 11..19) 100 else 1
    var isIthTeal = false

    override fun sort() {
        val n: Int = listNum.size
        var h = 1
        while (h < n / 3) h = 3 * h + 1  // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        MainScope().launch {
            while (h >= 1) {
                for (i in h until n) {
                    var j = i
                    listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)
                    while (j >= h && listNum[j] < listNum[j - h]) {
                        exchange(listNum, j, j - h)
                        updateList(j,j-h)
                        j -= h
                    }
                    listSort.getChildAt(i).setBackgroundResource(R.color.blue)
                    listSort.getChildAt(j).setBackgroundResource(R.color.blue)
                }
                h /= 3
            }
            delay(300)
            paint()
            notifyUI.updateUI()
        }
    }

    private suspend fun updateList(blue: Int, teal: Int) {
        adapter.notifyItemChanged(blue)
        delay(10)
        adapter.notifyItemChanged(teal)
        delay(267)
    }

}