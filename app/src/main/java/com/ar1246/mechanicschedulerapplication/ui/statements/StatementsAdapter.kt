package com.ar1246.mechanicschedulerapplication.ui.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.db.StatementEntity
import kotlinx.android.synthetic.main.statement_custom_row.view.*
import java.text.DateFormat

class StatementsAdapter : RecyclerView.Adapter<StatementsAdapter.MyViewHolder>() {

    private var statementsList = emptyList<StatementEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.statement_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = statementsList[position]

        holder.itemView.duration_text.text = currentItem.timeElapsed
        holder.itemView.date_text.text =
            DateFormat.getDateInstance().format(currentItem.startTime).toString()
        holder.itemView.time_text.text = "${
            DateFormat.getTimeInstance().format(currentItem.startTime)
        } - ${DateFormat.getTimeInstance().format(currentItem.endTime)}"
    }

    override fun getItemCount(): Int {
        return statementsList.size
    }

    fun setData(statement: List<StatementEntity>) {
        this.statementsList = statement
        notifyDataSetChanged()
    }
}