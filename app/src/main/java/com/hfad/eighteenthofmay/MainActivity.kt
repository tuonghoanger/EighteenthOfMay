package com.hfad.eighteenthofmay

import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import com.hfad.eighteenthofmay.sorting.*


class MainActivity() : AppCompatActivity(), Sort.OnComplete {

    private lateinit var shapeAdapter: ShapeAdapter
    private val newList    by lazy { findViewById<TextView>(R.id.button) }
    private val sortButton by lazy { findViewById<TextView>(R.id.sort_button) }

    private val seekBar   by lazy { findViewById<SeekBar>(R.id.seekBar)    }
    private val listSort  by lazy { findViewById<RecyclerView>(R.id.list_sort) }
    private val increase  by lazy { findViewById<ImageView>(R.id.increase) }
    private val decrease  by lazy { findViewById<ImageView>(R.id.decrease) }
    private val speedMode by lazy { findViewById<ImageView>(R.id.speed)    }
    var isSpeedOn = false

    private val selection by lazy { findViewById<TextView>(R.id.selection) }
    private val insertion by lazy { findViewById<TextView>(R.id.insertion) }
    private val bubble    by lazy { findViewById<TextView>(R.id.bubble)    }
    private val heap      by lazy { findViewById<TextView>(R.id.shell)     }
    private val merge     by lazy { findViewById<TextView>(R.id.merge)     }
    private val quick     by lazy { findViewById<TextView>(R.id.quick)     }
    lateinit var listSortType : List<TextView>
    private var sortType  = ""

    private val displayMetrics: DisplayMetrics by lazy { applicationContext.resources.displayMetrics }
    private val dpHeight: Float by lazy { displayMetrics.heightPixels / displayMetrics.density }
    private val dpWidth : Float by lazy { displayMetrics.widthPixels / displayMetrics.density }

    private val numCount by lazy { findViewById<TextView>(R.id.info) }
    private val sortInfo by lazy { findViewById<ImageView>(R.id.sort_info) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        sortButton.isEnabled = false
        seekBar.max = ((dpWidth-2*2)/(0.5*2*5)).toInt()
        seekBar.min = 6
        seekBar.progress = 6

        val width = "width : $dpWidth"
        numCount.text = width

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val x = "list contains $progress nums"
                numCount.text = x
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

        listSortType = listOf(selection,insertion,bubble,heap,merge,quick)
        listSortType.forEach { textView ->
            textView.setOnClickListener{
                sortInfo.isVisible = true
                sortButton.isEnabled = true
                sortType = textView.text.toString()
                listSortType.forEach { textSort ->
                    if (textSort==it) {
                        textSort.setTextColor(ContextCompat.getColor(this, R.color.sort_color_pressed))
                        textSort.setBackgroundResource(R.drawable.sort_bg_pressed)
                    }
                    else {
                        textSort.setTextColor(TextView(this).textColors)
                        textSort.setBackgroundResource(R.drawable.sort_background)
                    }
                }
            }
        }

        sortInfo.setOnClickListener {
            sortDialog(sortType)
        }

        speedMode.setOnClickListener {
            if (!isSpeedOn){
                isSpeedOn = !isSpeedOn
                speedMode.setColorFilter(ContextCompat.getColor(this, R.color.purple))
                Toast.makeText(this,"Speed Mode is On",Toast.LENGTH_SHORT).show()
            }
            else {
                isSpeedOn = !isSpeedOn
                speedMode.setColorFilter(ContextCompat.getColor(this, R.color.blue))
                Toast.makeText(this,"Speed Mode is Off",Toast.LENGTH_SHORT).show()
            }
        }

        sortButton.setOnClickListener {
            seekBar.isEnabled = false
            sortButton.isEnabled = false
            newList.isEnabled = false
            increase.isEnabled = false
            decrease.isEnabled = false
            speedMode.isEnabled = false
            listSortType.forEach {
                it.isEnabled = false
            }
            sortButton.setTextColor(ContextCompat.getColor(this, R.color.purple))
            when (sortType) {
                "Selection Sort" -> sortList(Selection(listSort, shapeAdapter, this))
                "Insertion Sort" -> sortList(Insertion(listSort, shapeAdapter, this))
                "Bubble Sort"    -> sortList(Bubble(listSort, shapeAdapter, this))
                "Shell Sort"     -> sortList(Shell(listSort, shapeAdapter, this))
                "Merge Sort"     -> Toast.makeText(this,"not yet",Toast.LENGTH_SHORT).show()
                "Quick Sort"     -> Toast.makeText(this,"not yet",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sortDialog(sortMethod: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(sortMethod)
        when (sortType) {
            "Selection Sort" -> builder.setMessage(R.string.selection)
            "Insertion Sort" -> builder.setMessage(R.string.insertion)
            "Bubble Sort"    -> builder.setMessage(R.string.bubble)
            "Shell Sort"     -> builder.setMessage(R.string.shell)
            "Merge Sort"     -> builder.setMessage(R.string.merge)
            "Quick Sort"     -> builder.setMessage(R.string.quick)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun sortList(sortMethod: Sort) {
        if (isSpeedOn) sortMethod.speedSort()
        else sortMethod.sort()
    }

    override fun updateUI() {
        seekBar.isEnabled = true
        sortButton.isEnabled = true
        sortButton.setTextColor(TextView(this).textColors)
        newList.isEnabled = true
        increase.isEnabled = true
        decrease.isEnabled = true
        speedMode.isEnabled = true
        listSortType.forEach {
            it.isEnabled = true
        }
    }

    fun createListSort(count: Int) {
        shapeAdapter = ShapeAdapter(count, this, dpWidth)
        listSort.apply {
            adapter = shapeAdapter
        }
    }

}