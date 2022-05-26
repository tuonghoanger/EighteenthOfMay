package com.hfad.eighteenthofmay.recyclerview

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.hfad.eighteenthofmay.R
import kotlin.random.Random

class ShapeAdapter(val count: Int, val context: Context, val totalWidth: Float) : RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder?>() {
    val listNum = List(count) { Random.nextFloat() } as MutableList<Float>
    val widthEach = (totalWidth - 2 * 2) / (0.5 * 2 * count)
    val toPixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthEach.toFloat(), context.resources.displayMetrics)

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
            rectangle.layoutParams.width = (toPixel).toInt() - 2
        }
    }

}