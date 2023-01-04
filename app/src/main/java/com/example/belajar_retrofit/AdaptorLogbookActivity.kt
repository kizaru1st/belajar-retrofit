package com.example.belajar_retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belajar_retrofit.R
import com.example.belajar_retrofit.datamodels.*
import kotlinx.android.synthetic.main.activity_logbook.view.*
import kotlinx.android.synthetic.main.item_story.view.*

class AdaptorLogbookActivity (private var list1 : List<LogbooksItem>) : RecyclerView.Adapter<AdaptorLogbookActivity.LogbookViewHolder>() {

    inner class LogbookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogbookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return LogbookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list1.size
    }

    override fun onBindViewHolder(holder: LogbookViewHolder, position: Int) {
        val currentItem = list1[position]
        holder.itemView.apply {
            tvKegiatan.text = currentItem.activities
        }
    }
}