package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.*

class Selection(val listSort: RecyclerView, val adapter: ShapeAdapter, val notifyUI: Sorting.OnComplete) : Sorting {
    private val listSize = adapter.listNum.size
    private val listNum = adapter.listNum

    override fun sort() {
        MainScope().launch {
            for (i in 0 until listSize) {
                val min = findMin(i)
                exchange(listNum, i, min)
                updateList(i, min)
            }
            notifyUI.updateUI()
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
    //    blink(listSort.getChildAt(min))
        for (j in start + 1 until listSize) {
         //   blink(listSort.getChildAt(j))
         //   listSort.getChildAt(j).setBackgroundResource(R.color.yellow)
         //   delay(60)
            if (listNum[j] < listNum[min]) {
            //    stopBlink(listSort.getChildAt(min))
                min = j
             //   blink(listSort.getChildAt(min))
            }
       //     else listSort.getChildAt(j).setBackgroundResource(R.color.blue)
              //  stopBlink(listSort.getChildAt(j))
        }
        return min
    }

}