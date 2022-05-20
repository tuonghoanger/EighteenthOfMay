package com.hfad.eighteenthofmay.sorting

class Selection {
    companion object {

        fun sort(list: MutableList<Int>) {
            val s = list.size
            for (i in 0 until s) {
                var min = i
                for (j in i + 1 until s) {
                    if (list[j] < list[min]) min = j
                }
                exchange(list,i,min)
            }
        }

        private fun exchange(list: MutableList<Int>, i: Int, j: Int) {
            val t = list[i]
            list[i] = list[j]
            list[j] = t
        }

    }
}