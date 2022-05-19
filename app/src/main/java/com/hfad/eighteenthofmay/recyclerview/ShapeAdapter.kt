package com.hfad.eighteenthofmay.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.databinding.TestLayoutBinding
import java.util.*

class ShapeAdapter(val count : Int) : RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewHolder {
        val binding = TestLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShapeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShapeViewHolder, position: Int) {
        holder.bind("i'm at pos $position")
    }

    override fun getItemCount(): Int {
        return count
    }

    inner class ShapeViewHolder(val binding: TestLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text : String) {
            binding.textHere.text = text
        }
    }
}