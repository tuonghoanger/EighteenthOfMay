package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InsertionNoExchange(listSort: RecyclerView,adapter: ShapeAdapter,notifyUI: Sort.OnComplete) : Sort(listSort,adapter,notifyUI)  {

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

    private suspend fun updateList(start: Int, itemCount: Int) {
        adapter.notifyItemRangeChanged(start, itemCount)
        delay(50)
    }

}