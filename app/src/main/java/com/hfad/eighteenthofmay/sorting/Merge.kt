package com.hfad.eighteenthofmay.sorting

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Merge(val context : Context, listSort: RecyclerView, adapter: ShapeAdapter, notifyUI: Sort.OnComplete) : Sort(listSort, adapter, notifyUI) {
    lateinit var aux: MutableList<Float>

    override fun speedSort() {
        MainScope().launch {
            aux = listNum.toMutableList()
            speedSort(listNum, 0, listSize - 1)
            delay(200)
            paint()
            notifyUI.updateUI()
        }
    }

    suspend fun speedSort(listNum: MutableList<Float>, lo: Int, hi: Int) {
        if (hi <= lo) return
        val mid = lo + (hi - lo) / 2
        speedSort(listNum, lo, mid)
        speedSort(listNum, mid + 1, hi)
        speedMerge(listNum, lo, mid, hi)
    }

    suspend fun speedMerge(listNum: MutableList<Float>, lo: Int, mid: Int, hi: Int) {
        var i = lo
        var j = mid + 1
        for (k in lo..hi)          // Copy a[lo..hi] to aux[lo..hi]
            aux[k] = listNum[k]    // speedMerge : copy to auxiliary array then merge tol listNum
        for (k in lo..hi) {
            if (i > mid) listNum[k] = aux[j++]
            else if (j > hi) listNum[k] = aux[i++]
            else if (aux[j] < aux[i]) listNum[k] = aux[j++]
            else {
                listNum[k] = aux[i++]
            }
            updateList(k)
        }
    }

    private suspend fun updateList(i: Int) {
        adapter.notifyItemChanged(i)
        delay(37)
    }

    // Sort Slow
    override fun sort() {
        MainScope().launch {
            aux = listNum.toMutableList()
            sort(listNum, 0, listSize - 1)
            delay(200)
            paint()
            notifyUI.updateUI()
        }
    }

    suspend fun sort(listNum: MutableList<Float>, lo: Int, hi: Int) {
        if (hi <= lo) return
        val mid = lo + (hi - lo) / 2
        sort(listNum, lo, mid)
        sort(listNum, mid + 1, hi)
        merge(listNum, lo, mid, hi)
    }


    // merge to auxiliary array then copy to listNum
    suspend fun merge(listNum: MutableList<Float>, lo: Int, mid: Int, hi: Int) {
        var i = lo
        var j = mid + 1

        // merge to auxiliary array
        for (k in lo..hi) {

            if (i > mid) {
                listSort.getChildAt(j).setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                delay(50)
                aux[k] = listNum[j++]

                if (j <= hi) listSort.getChildAt(j).setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            } else if (j > hi) {
                listSort.getChildAt(i).setBackgroundColor(ContextCompat.getColor(context, R.color.blue))

                delay(50)
                aux[k] = listNum[i++]
                if (i <= mid) listSort.getChildAt(i).setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            } else if (listNum[j] < listNum[i]) {
                listSort.getChildAt(j).setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                delay(50)
                aux[k] = listNum[j++]

                if (j <= hi) listSort.getChildAt(j).setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            } else {
                listSort.getChildAt(i).setBackgroundColor(ContextCompat.getColor(context, R.color.blue))

                delay(50)
                aux[k] = listNum[i++]
                if (i <= mid) listSort.getChildAt(i).setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            }
            delay(50)

        }

        //copy from auxiliary to listNum
        for (k in lo..hi) {
            listNum[k] = aux[k]
            adapter.notifyItemChanged(k)
            delay(50)
        }
    }


}