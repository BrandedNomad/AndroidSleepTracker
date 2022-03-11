package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ItemViewHolder>() {

    //Create a viewHolder
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sleepQuality: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
    }

    //Create data object
    var data = listOf<SleepNight>()
        set(value){ //Create a custom setter
            field = value
            notifyDataSetChanged() //tells recycler view that data has changed and causes a redraw
        }

    //Returns the size of the list
    //tells the recyclerview how many items there are in the list
    override fun getItemCount() = data.size

    //Called by RecyclerView to display the data at the specified position.
    //This is where data is binded to the viewHolder
    override fun onBindViewHolder(holder:ItemViewHolder, position:Int){
        var item =  data[position]
        if(item.sleepQuality <= 2){
            holder.sleepQuality!!.setTextColor(Color.RED)
            holder.quality!!.setTextColor(Color.RED)
        } else {
            holder.quality!!.setTextColor(Color.BLACK)
            holder.sleepQuality!!.setTextColor(Color.BLACK)
        }
        holder.sleepQuality.text = item.sleepQuality.toString()
        holder.quality.setText(
            when(item.sleepQuality){
                0 -> "Very Bad"
                1 -> "Bad"
                2 -> "Poor"
                3 -> "OK"
                4 -> "Good"
                5 -> "Excellent"
                else -> "Can't Say"
            }
        )
        holder.qualityImage.setImageResource(
            when(item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }
        )

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false) as View
        return ItemViewHolder(view)

    }
}