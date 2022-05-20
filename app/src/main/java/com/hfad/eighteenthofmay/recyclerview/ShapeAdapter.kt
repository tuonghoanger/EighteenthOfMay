package com.hfad.eighteenthofmay.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import kotlin.random.Random as Random

class ShapeAdapter(val count : Int) : RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder?>() {
    val listNum = List(count) { Random.nextFloat() } as MutableList<Float>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rectangle_shape,parent,false)
        return ShapeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShapeViewHolder, position: Int) {
        holder.bind(listNum[position])
    }

    override fun getItemCount(): Int {
        return count
    }

    inner class ShapeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val rectangle by lazy { view.findViewById<FrameLayout>(R.id.rectangle) }

        fun bind(height : Float) {
            rectangle.apply {
                scaleY = height
            }
        }

    }

}