package com.hfad.eighteenthofmay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.hfad.eighteenthofmay.databinding.ActivityMainBinding
import android.widget.Toast

import android.widget.SeekBar

import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.seekBar.max = 100
        binding.seekBar.min = 5
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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

        binding.listSort.apply {
            adapter = ShapeAdapter(50)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rotationX = 180f
        }

        binding.sortButton.setOnClickListener {
            binding.blue?.apply {
                pivotY = this.height.toFloat()
                scaleY = Random.nextFloat()
            }

        }

    }

    fun createListSort(count: Int) {
        binding.listSort.apply {
            adapter = ShapeAdapter(count)
        }

    }
}