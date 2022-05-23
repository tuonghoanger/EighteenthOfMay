package com.hfad.eighteenthofmay.sorting

import android.util.Log
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Selection(val listSort: RecyclerView, val adapter: ShapeAdapter, val complete: Sorting.OnComplete) : Sorting {
    private val listSize = adapter.listNum.size

    override fun sort() {
        MainScope().launch {
            for (i in 0 until listSize) {
                val min = findMin(i)
                exchange(adapter.listNum, i, min)
                updateList(i, min)
            }
            complete.updateUI()
        }
    }

    private suspend fun updateList(i: Int, min: Int) {
        adapter.notifyItemChanged(i)
        delay(10)
        adapter.notifyItemChanged(min)
        delay(269)
        listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)
        listSort.getChildAt(i).animation?.cancel()
        delay(10)
    }

    private suspend fun findMin(start: Int): Int {
        var min = start
        blink(listSort.getChildAt(min))
        for (j in start + 1 until listSize) {
            blink(listSort.getChildAt(j))
            delay(60)
            if (adapter.listNum[j] < adapter.listNum[min]) {
                stopBlink(listSort.getChildAt(min))
                min = j
            }
            else stopBlink(listSort.getChildAt(j))
        }
        return min
    }

}