package com.hfad.eighteenthofmay.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import com.hfad.eighteenthofmay.databinding.RectangleShapeBinding

import kotlin.random.Random as Random

class ShapeAdapter(val count : Int) : RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder?>() {
    val listNum = List(count) { Random.nextFloat() } as MutableList<Float>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewHolder {
        val binding = RectangleShapeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
   //     listNum.sort()
        return ShapeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShapeViewHolder, position: Int) {
        holder.bind(listNum[position])
    }

    override fun getItemCount(): Int {
        return count
    }

    inner class ShapeViewHolder(val binding: RectangleShapeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(height : Float) {
            binding.rectangle.apply {
                scaleY = height
            }
        }

        fun changeColor(){
            binding.rectangle.setBackgroundResource(R.color.teal_200)
        }
    }


}