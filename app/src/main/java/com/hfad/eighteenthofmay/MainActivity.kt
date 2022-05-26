package com.hfad.eighteenthofmay

import android.content.Context
import android.graphics.Color
import android.graphics.Insets
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import com.hfad.eighteenthofmay.sorting.Insertion
import com.hfad.eighteenthofmay.sorting.Selection
import com.hfad.eighteenthofmay.sorting.Sorting
import kotlin.random.Random


class MainActivity() : AppCompatActivity(), Sorting.OnComplete {

    private lateinit var shapeAdapter: ShapeAdapter

    //  private val text by lazy { findViewById<TextView>(R.id.text) }
    private val newList by lazy { findViewById<TextView>(R.id.button) }
    private val sortButton by lazy { findViewById<TextView>(R.id.sort_button) }

    private val seekBar by lazy { findViewById<SeekBar>(R.id.seekBar) }
    private val listSort by lazy { findViewById<RecyclerView>(R.id.list_sort) }
    private val increase by lazy { findViewById<TextView>(R.id.increase) }
    private val decrease by lazy { findViewById<TextView>(R.id.decrease) }

    private val selection by lazy { findViewById<TextView>(R.id.selection) }
    private val insertion by lazy { findViewById<TextView>(R.id.insertion) }
    private val bubble by lazy { findViewById<TextView>(R.id.bubble) }
    private val heap by lazy { findViewById<TextView>(R.id.heap) }
    private val merge by lazy { findViewById<TextView>(R.id.merge) }
    private val quick by lazy { findViewById<TextView>(R.id.quick) }

//    private val one by lazy { findViewById<View>(R.id.blue) }
//    private val two by lazy { findViewById<View>(R.id.blue2) }
//    private val three by lazy { findViewById<View>(R.id.blue3) }

    private val displayMetrics: DisplayMetrics by lazy { applicationContext.resources.displayMetrics }
    private val dpHeight: Float by lazy { displayMetrics.heightPixels / displayMetrics.density }
    private val dpWidth: Float by lazy { displayMetrics.widthPixels / displayMetrics.density }

    private val info: TextView by lazy { findViewById<TextView>(R.id.info) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        seekBar.max = ((dpWidth-2*2)/(0.5*2*5)).toInt()
        seekBar.min = 6
        seekBar.progress = 6

        val width = "width : $dpWidth"
        info.text = width

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val x = "list contains $progress nums"
                info.text = x
                createListSort(progress)
            }
        })

        shapeAdapter = ShapeAdapter(seekBar.progress, this, dpWidth)
        listSort.apply {
            adapter = shapeAdapter
            layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return false
                    }
                }

            rotationX = 180f
        }

        increase.setOnClickListener {
            seekBar.progress++
        }

        decrease.setOnClickListener {
            seekBar.progress--
        }

        newList.setOnClickListener {
              createListSort(seekBar.progress)

        }

        sortButton.setOnClickListener {
            seekBar.isEnabled = false
            sortButton.isEnabled = false
            newList.isEnabled = false
            sortButton.setTextColor(ContextCompat.getColor(this, R.color.purple))
            sortList(Insertion(listSort, shapeAdapter, this))
        }

    }

    private fun sortList(sortMethod: Sorting) {
        sortMethod.sort()
    }

    override fun updateUI() {
        seekBar.isEnabled = true
        sortButton.isEnabled = true
        sortButton.setTextColor(TextView(this).textColors)
        newList.isEnabled = true
    }

    fun getRandomColor(): Int {
        val rnd = Random
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun createListSort(count: Int) {
        shapeAdapter = ShapeAdapter(count, this, dpWidth)
        listSort.apply {
            adapter = shapeAdapter
        }

    }

}