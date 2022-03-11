package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: ListAdapter<SleepNight, SleepNightAdapter.ItemViewHolder>(SleepNightDiffCallback()) {

    //Create a viewHolder
    class ItemViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val sleepQuality: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(
        holder: ItemViewHolder,
        item: SleepNight,
        res: Resources
        ) {
            holder.sleepQuality.text = item.sleepQuality.toString()
            holder.quality.setText(
                when (item.sleepQuality) {
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
                when (item.sleepQuality) {
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

        companion object {
            fun from(parent:ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false) as View
                return ItemViewHolder(view)
            }
        }
    }



    //Called by RecyclerView to display the data at the specified position.
    //This is where data is binded to the viewHolder
    override fun onBindViewHolder(holder:ItemViewHolder, position:Int){
        var item =  getItem(position)
        var res = holder.itemView.context.resources

        //
        holder.bind(holder, item,res)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)

    }


}

class SleepNightDiffCallback: DiffUtil.ItemCallback<SleepNight>(){
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }


}

