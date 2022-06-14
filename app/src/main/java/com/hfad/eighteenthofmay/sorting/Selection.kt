package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.*

class Selection(listSort: RecyclerView,adapter: ShapeAdapter,notifyUI: Sort.OnComplete) : Sort(listSort,adapter,notifyUI) {
    val speed : Long = if(listSize<11) 500 else if (listSize in 11..20) 200 else if (listSize in 21..39) 50 else 15

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

    override fun speedSort() {
        MainScope().launch {
            for (i in 0 until listSize) {
                var min = i
                for (j in i until listSize){
                    delay(3)
                    if (listNum[j] < listNum[min]) {
                        min = j
                    }
                }
                exchange(listNum, i, min)
                adapter.notifyItemChanged(i)
                delay(7)
                adapter.notifyItemChanged(min)
                delay(7)
            }
            delay(370)
            paint()
            notifyUI.updateUI()
        }
    }

    private suspend fun updateList(i: Int, min: Int) {
        adapter.notifyItemChanged(i)
        delay(10)
        adapter.notifyItemChanged(min)
        delay(267)
        listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)
        listSort.getChildAt(i).animation?.cancel()
    }

    private suspend fun findMin(start: Int): Int {
        var min = start
        blink(listSort.getChildAt(min))
        for (j in start + 1 until listSize) {
            listSort.getChildAt(j).setBackgroundResource(R.color.yellow)
            delay(speed)
            if (listNum[j] < listNum[min]) {
                stopBlink(listSort.getChildAt(min))
                min = j
                blink(listSort.getChildAt(min))
            }
            else listSort.getChildAt(j).setBackgroundResource(R.color.blue)
        }
        return min
    }

}