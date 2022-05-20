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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import androidx.core.os.HandlerCompat.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.eighteenthofmay.databinding.ActivityMainBinding
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var shapeAdapter: ShapeAdapter
    private val button by lazy { findViewById<Button>(R.id.button) }
    private val one by lazy { findViewById<View>(R.id.blue) }
    private val two by lazy { findViewById<View>(R.id.blue2) }
    private val three by lazy { findViewById<View>(R.id.blue3) }
    private val seekBar by lazy { findViewById<SeekBar>(R.id.seekBar) }
    var listChangeable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        seekBar.max = 100
        seekBar.min = 5
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val x = "we have ${binding.seekBar.progress}"
                binding.text.text = x
                createListSort(progress)
            }
        })
        shapeAdapter = ShapeAdapter(30)
        binding.listSort.apply {
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
            button.isEnabled = false
            seekBar.isEnabled = false
            i = 0
            val mainHandler = Handler(Looper.getMainLooper())

            mainHandler.post(object : Runnable {
                override fun run() {
                    if (i<shapeAdapter.itemCount)   {
                        binding.listSort.getChildAt(i++).setBackgroundResource(R.color.teal_200)
                        mainHandler.postDelayed(this, 150)
                    }
                    else {
                        for (j in 0 until  shapeAdapter.itemCount) {
                            binding.listSort.getChildAt(j).setBackgroundResource(R.color.blue)
                        }
                        button.isEnabled = true
                        seekBar.isEnabled = true
                    }
                }
            })

        }

        binding.sortButton.setOnClickListener {


        }

    }



    fun changeScale(view : View){
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
        binding.listSort.apply {
            adapter = shapeAdapter
        }

    }
}