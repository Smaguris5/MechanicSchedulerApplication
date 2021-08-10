package com.ar1246.mechanicschedulerapplication.ui.time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.db.StatementEntity
import kotlinx.android.synthetic.main.time_custom_row.view.*
import java.text.DateFormat

class TimeAdapter : RecyclerView.Adapter<TimeAdapter.MyViewHolder>() {

    private var timesList = emptyList<StatementEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.time_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = timesList[position]

        holder.itemView.start_time_text.text =
            DateFormat.getTimeInstance().format(currentItem.startTime).toString()
        holder.itemView.end_time_text.text =
            DateFormat.getTimeInstance().format(currentItem.endTime).toString()
        holder.itemView.time_elapsed_text.text = currentItem.timeElapsed
    }

    override fun getItemCount(): Int {
        return timesList.size
    }

    fun setData(statement: List<StatementEntity>) {
        this.timesList = statement.reversed()
        notifyDataSetChanged()
    }


}