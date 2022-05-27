package com.hfad.eighteenthofmay.sorting

import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Insertion(listSort: RecyclerView,adapter: ShapeAdapter,notifyUI: Sort.OnComplete) : Sort(listSort,adapter,notifyUI)  {
    val speed : Long = if(listSize<11) 400 else if (listSize in 11..19) 150 else if (listSize in 21..39) 10 else 1

    override fun sort() {
        MainScope().launch {
            for (i in 1 until listSize) {
                listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)
                var j = i
                while (j > 0 && listNum[j] < listNum[j - 1]) {
                //    stopBlink(listSort.getChildAt(j))
                    listSort.getChildAt(j).setBackgroundResource(R.color.blue)
                    exchange(listNum, j, j-1)
                    updateList(j,j-1)
                    listSort.getChildAt(i).setBackgroundResource(R.color.teal_200)  // not to repeat
                    j--
                //    blink(listSort.getChildAt(j))
                    delay(speed)
                }
                listSort.getChildAt(i).setBackgroundResource(R.color.blue)
                stopBlink(listSort.getChildAt(j))
            }
            delay(350)
            paint()
            notifyUI.updateUI()
        }
    }

    private suspend fun updateList(start: Int, itemCount: Int) {
//        adapter.notifyItemRangeChanged(start, itemCount)
//        delay(40)
        adapter.notifyItemChanged(start)
        delay(10)
        adapter.notifyItemChanged(itemCount)
        delay(269)
    }

}