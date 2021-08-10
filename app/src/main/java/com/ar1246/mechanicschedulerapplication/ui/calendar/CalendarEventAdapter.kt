package com.ar1246.mechanicschedulerapplication.ui.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar1246.mechanicschedulerapplication.R
import com.github.sundeepk.compactcalendarview.domain.Event
import kotlinx.android.synthetic.main.calendar_custom_row.view.*
import java.text.DateFormat

class CalendarEventAdapter : RecyclerView.Adapter<CalendarEventAdapter.MyViewHolder>() {
    private var eventsList = emptyList<Event>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.calendar_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventsList[position]

        holder.itemView.title_text.text =
            DateFormat.getDateInstance().format(currentItem.timeInMillis).toString()
        holder.itemView.description_text.text = currentItem.data.toString()
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    fun setData(event: List<Event>) {
        this.eventsList = event
        notifyDataSetChanged()
    }

}