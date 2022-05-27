package com.hfad.eighteenthofmay

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import com.hfad.eighteenthofmay.sorting.Insertion
import com.hfad.eighteenthofmay.sorting.Selection
import com.hfad.eighteenthofmay.sorting.Sort

class MainActivity() : AppCompatActivity(), Sort.OnComplete {

    private lateinit var shapeAdapter: ShapeAdapter
    private val newList    by lazy { findViewById<TextView>(R.id.button) }
    private val sortButton by lazy { findViewById<TextView>(R.id.sort_button) }

    private val seekBar  by lazy { findViewById<SeekBar>(R.id.seekBar) }
    private val listSort by lazy { findViewById<RecyclerView>(R.id.list_sort) }
    private val increase by lazy { findViewById<TextView>(R.id.increase) }
    private val decrease by lazy { findViewById<TextView>(R.id.decrease) }

    private val selection by lazy { findViewById<TextView>(R.id.selection) }
    private val insertion by lazy { findViewById<TextView>(R.id.insertion) }
    private val bubble    by lazy { findViewById<TextView>(R.id.bubble) }
    private val heap      by lazy { findViewById<TextView>(R.id.shell) }
    private val merge     by lazy { findViewById<TextView>(R.id.merge) }
    private val quick     by lazy { findViewById<TextView>(R.id.quick) }
    private var sortType  = ""

    private val displayMetrics: DisplayMetrics by lazy { applicationContext.resources.displayMetrics }
    private val dpHeight: Float by lazy { displayMetrics.heightPixels / displayMetrics.density }
    private val dpWidth : Float by lazy { displayMetrics.widthPixels / displayMetrics.density }

    private val info: TextView by lazy { findViewById<TextView>(R.id.info) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        sortButton.isEnabled = false
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

        val listSortType : List<TextView> = listOf(selection,insertion,bubble,heap,merge,quick)
        listSortType.forEach { textView ->
            textView.setOnClickListener{
                sortButton.isEnabled = true
                sortType = textView.text.toString()
                listSortType.forEach { textSort ->
                    if (textSort==it) textSort.setTextColor(ContextCompat.getColor(this, R.color.sort_color))
                    else textSort.setTextColor(TextView(this).textColors)
                }
            }
        }

        sortButton.setOnClickListener {
            seekBar.isEnabled = false
            sortButton.isEnabled = false
            newList.isEnabled = false
            sortButton.setTextColor(ContextCompat.getColor(this, R.color.purple))
            when (sortType) {
                "Selection Sort" -> sortList(Selection(listSort, shapeAdapter, this))
                "Insertion Sort" -> sortList(Insertion(listSort, shapeAdapter, this))
                "Bubble Sort"    -> Toast.makeText(this,"not yet",Toast.LENGTH_SHORT).show()
                "Shell Sort"     -> Toast.makeText(this,"not yet",Toast.LENGTH_SHORT).show()
                "Merge Sort"     -> Toast.makeText(this,"not yet",Toast.LENGTH_SHORT).show()
                "Quick Sort"     -> Toast.makeText(this,"not yet",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sortList(sortMethod: Sort) {
        sortMethod.sort()
    }

    override fun updateUI() {
        seekBar.isEnabled = true
        sortButton.isEnabled = true
        sortButton.setTextColor(TextView(this).textColors)
        newList.isEnabled = true
    }

    fun createListSort(count: Int) {
        shapeAdapter = ShapeAdapter(count, this, dpWidth)
        listSort.apply {
            adapter = shapeAdapter
        }
    }

}