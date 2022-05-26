package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Insertion(
    val listSort: RecyclerView,
    val adapter: ShapeAdapter,
    val notifyUI: Sorting.OnComplete
) : Sorting {
    private val listSize = adapter.listNum.size
    private val listNum = adapter.listNum

    override fun sort() {
        MainScope().launch {
            for (i in 1 until listSize) {
                val item = listNum[i]
                var j = i
                while (j > 0 && item < listNum[j - 1]) {
                    listNum[j] = listNum[j - 1]
                    j--
                }
                listNum[j] = item
                updateList(j, i - j + 1)
            }
            delay(350)
            paint()
            notifyUI.updateUI()
        }
    }

    private suspend fun paint() {
        (0 until listSize).forEach {
            listSort.getChildAt(it).setBackgroundResource(R.color.teal_200)
            delay(5)
        }
    }

    private suspend fun updateList(start: Int, itemCount: Int) {
        adapter.notifyItemRangeChanged(start, itemCount)
        delay(50)
    }

}