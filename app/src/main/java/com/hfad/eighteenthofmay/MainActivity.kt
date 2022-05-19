package com.hfad.eighteenthofmay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.hfad.eighteenthofmay.databinding.ActivityMainBinding
import android.widget.Toast

import android.widget.SeekBar

import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.eighteenthofmay.recyclerview.ShapeAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapterShape : ShapeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapterShape = ShapeAdapter(50)
        binding.seekBar.max = 100
        binding.seekBar.min = 10
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            }
        })
        binding.sortButton.setOnClickListener {
            val x = "we have ${binding.seekBar.progress}"
            binding.text.text = x
            createListSort(binding.seekBar.progress)
        }

        binding.listSort.apply {
            adapter = adapterShape
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }


    }

    fun createListSort(count : Int){
        binding.listSort.apply {
            adapter?.notifyItemInserted(1)
        }

    }
}