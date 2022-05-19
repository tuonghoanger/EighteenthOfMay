package com.hfad.eighteenthofmay.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.databinding.RectangleShapeBinding

import kotlin.random.Random as Random

class ShapeAdapter(val count : Int) : RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder?>() {
    val listNum = List(count) { Random.nextFloat() } as MutableList<Float>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewHolder {
        val binding = RectangleShapeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShapeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShapeViewHolder, position: Int) {
        holder.bind(listNum[position],position)
    }

    override fun getItemCount(): Int {
        return count
    }

    inner class ShapeViewHolder(val binding: RectangleShapeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(height : Float,pos : Int) {
            binding.rectangle.apply {
                scaleY = height
            }
        }
    }
}