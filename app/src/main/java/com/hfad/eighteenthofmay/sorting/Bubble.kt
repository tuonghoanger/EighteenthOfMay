package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Bubble(listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort, adapter, notifyUI) {

    override fun sort() {
        val n = listNum.count() // = numbers in list so last num index is n-1
        MainScope().launch {
            for (i in n - 1 downTo 0) {
                for (j in 0 until i) {
                    if (j==0) {
                        listSort.getChildAt(j).setBackgroundResource(R.color.teal_200)
                    }
                    listSort.getChildAt(j + 1).setBackgroundResource(R.color.teal_200)
                    if (listNum[j] > listNum[j + 1]) {
                        exchange(listNum, j, j + 1)
                        updateList(j, j + 1)
                    }
                    listSort.getChildAt(j).setBackgroundResource(R.color.blue)
                }
            }
            listSort.getChildAt(0).setBackgroundResource(R.color.teal_200)
            notifyUI.updateUI()
        }
    }

    override fun speedSort() {
        val n = listNum.count() // = numbers in list so last num index is n-1
        MainScope().launch {
            for (i in n - 1 downTo 0) {
                for (j in 0 until i) {
                    if (listNum[j] > listNum[j + 1]) {
                        exchange(listNum, j, j + 1)
                        adapter.notifyItemChanged(i)
                        delay(3)
                        adapter.notifyItemChanged(j)
                        delay(3)
                    }
                    delay(7)
                }
            }
            delay(370)
            paint()
            notifyUI.updateUI()
        }
    }

    private suspend fun updateList(i: Int, j: Int) {
        adapter.notifyItemChanged(i)
        delay(10)
        adapter.notifyItemChanged(j)
        delay(267)
    }

}