package com.hfad.eighteenthofmay.sorting

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Insertion(listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort, adapter, notifyUI) {
    val speed: Long = if (listSize < 11) 200 else if (listSize in 11..19) 100 else 1
    var isIthTeal = false

    override fun sort() {
        MainScope().launch {
            for (i in 1 until listSize) {
                listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)
                var j = i
                while (j > 0 && listNum[j] < listNum[j - 1]) {
                    //adapter.notifyItemChanged still exchange look
                    if (j == i) listSort.getChildAt(i).setBackgroundResource(R.color.blue)
                    exchange(listNum, j, j - 1)
                    updateList(j, j - 1)
                    if (!isIthTeal) {
                        listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)  // not to repeat
                        isIthTeal = true
                    }
                    j--
                }
                listSort.getChildAt(i).setBackgroundResource(R.color.blue)
                isIthTeal = false
            }
            delay(300)
            paint()
            notifyUI.updateUI()
        }
    }

    override fun speedSort() {
        MainScope().launch {
            for (i in 1 until listSize) {
                var j = i
                while (j > 0 && listNum[j] < listNum[j - 1]) {
                    exchange(listNum, j, j - 1)
                    adapter.notifyItemChanged(j)
                    delay(3)
                    adapter.notifyItemChanged(j-1)
                    delay(3)
                    j--
                    delay(7)
                }
            }
            delay(370)
            paint()
            notifyUI.updateUI()
        }
    }

    private suspend fun updateList(start: Int, itemCount: Int) {
        adapter.notifyItemChanged(start)
        delay(10)
        adapter.notifyItemChanged(itemCount)
        delay(267)
    }

}