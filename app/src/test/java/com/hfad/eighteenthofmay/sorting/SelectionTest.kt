package com.hfad.eighteenthofmay.sorting

import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class SelectionTest {
    private val listNum = List(20) { Random.nextFloat() } as MutableList<Float>


    @Test
    fun sortTest(){
        val sorted = listNum.toMutableList()
        sorted.sort()

        assertEquals(listNum,sorted)
    }
}