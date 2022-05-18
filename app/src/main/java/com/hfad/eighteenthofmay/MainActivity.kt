package com.hfad.eighteenthofmay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.eighteenthofmay.databinding.ActivityMainBinding
import android.widget.Toast

import android.widget.SeekBar

import android.widget.SeekBar.OnSeekBarChangeListener




class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                binding.text.text = progress.toString()
                Toast.makeText(applicationContext, progress.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }
}