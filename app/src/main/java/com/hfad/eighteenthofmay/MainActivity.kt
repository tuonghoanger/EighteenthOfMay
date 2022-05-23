package com.hfad.eighteenthofmay

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import com.hfad.eighteenthofmay.sorting.Selection
import com.hfad.eighteenthofmay.sorting.Sorting
import kotlin.random.Random

class MainActivity() : AppCompatActivity() , Sorting.OnComplete{

    private lateinit var shapeAdapter: ShapeAdapter
    private val text by lazy { findViewById<TextView>(R.id.text) }
    private val newList by lazy { findViewById<Button>(R.id.button) }
    private val sortButton by lazy { findViewById<Button>(R.id.sort_button) }
    private val one by lazy { findViewById<View>(R.id.blue) }
    private val two by lazy { findViewById<View>(R.id.blue2) }
    private val three by lazy { findViewById<View>(R.id.blue3) }
    private val seekBar by lazy { findViewById<SeekBar>(R.id.seekBar) }
    private val listSort by lazy { findViewById<RecyclerView>(R.id.list_sort) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.max = 100
        seekBar.min = 5
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val x = "we have ${seekBar.progress}"
                text.text = x
                createListSort(progress)
            }
        })

        shapeAdapter = ShapeAdapter(50)
        listSort.apply {
            adapter = shapeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rotationX = 180f
        }

        newList.setOnClickListener {
          createListSort(seekBar.progress)
         //   blink(one)
        }

        sortButton.setOnClickListener {
            seekBar.isEnabled = false
            sortButton.isEnabled = false
            newList.isEnabled = false
            sortList(Selection(listSort,shapeAdapter,this))
            one.animation?.cancel()
        }

    }

    private fun sortList(sortMethod: Sorting) {
        sortMethod.sort()
    }

    override fun updateUI() {
        seekBar.isEnabled = true
        sortButton.isEnabled = true
        newList.isEnabled = true
    }

    fun getRandomColor(): Int {
        val rnd = Random
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun createListSort(count: Int) {
        shapeAdapter = ShapeAdapter(count)
        listSort.apply {
            adapter = shapeAdapter
        }

    }

}