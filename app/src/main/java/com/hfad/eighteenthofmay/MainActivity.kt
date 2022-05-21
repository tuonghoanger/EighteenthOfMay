package com.hfad.eighteenthofmay

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var shapeAdapter: ShapeAdapter
    private val text by lazy { findViewById<TextView>(R.id.text) }
    private val button by lazy { findViewById<Button>(R.id.button) }
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
        shapeAdapter = ShapeAdapter(30)
        listSort.apply {
            adapter = shapeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rotationX = 180f
        }

        one.apply {
            pivotY = height.toFloat()
            scaleY = Random.nextFloat()
        }

        var i = 0
        button.setOnClickListener {
//            button.isEnabled = false
//            seekBar.isEnabled = false
//            i = 0
//            val mainHandler = Handler(Looper.getMainLooper())
//
//            mainHandler.post(object : Runnable {
//                override fun run() {
//                    if (i > 0) listSort.getChildAt(i - 1).setBackgroundResource(R.color.blue)
//                    if (i < shapeAdapter.itemCount) {
//                        listSort.getChildAt(i++).setBackgroundResource(R.color.teal_200)
//                        mainHandler.postDelayed(this, 100)
//                    } else {
//                        button.isEnabled = true
//                        seekBar.isEnabled = true
//                    }
//                }
//            })

        }


        sortButton.setOnClickListener {
//            Selection.sort(shapeAdapter.listNum)
//            shapeAdapter.notifyDataSetChanged()
            sortButton.isEnabled = false
            seekBar.isEnabled = false

            MainScope().launch {
                for (j in 0 until shapeAdapter.itemCount){
                    listSort.getChildAt(j).setBackgroundResource(R.color.teal_200)
                    delay(100)
                    listSort.getChildAt(j).setBackgroundResource(R.color.blue)
                }

                sortButton.isEnabled = true
                seekBar.isEnabled = true
            }



        }

    }


    fun changeScale(view: View) {
        view.apply {
            pivotY = height.toFloat()
            scaleY = Random.nextFloat()
            setBackgroundColor(getRandomColor())
        }
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