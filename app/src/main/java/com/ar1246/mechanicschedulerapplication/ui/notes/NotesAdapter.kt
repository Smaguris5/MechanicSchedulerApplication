package com.ar1246.mechanicschedulerapplication.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.db.NoteEntity
import kotlinx.android.synthetic.main.note_custom_row.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    private var notesList = emptyList<NoteEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]
        holder.itemView.id_text.text = currentItem.noteId.toString()
        holder.itemView.car_text.text = currentItem.car
        holder.itemView.note_text.text = currentItem.description

        holder.itemView.noteRowLayout.setOnClickListener {
            val action = NotesFragmentDirections.actionNavNotesToUpdateNoteFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(note: List<NoteEntity>) {
        this.notesList = note
        notifyDataSetChanged()
    }
}