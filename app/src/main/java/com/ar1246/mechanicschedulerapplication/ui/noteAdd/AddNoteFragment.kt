package com.ar1246.mechanicschedulerapplication.ui.noteAdd

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.db.NoteEntity
import com.ar1246.mechanicschedulerapplication.ui.notes.NotesViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_add_note.view.*

class AddNoteFragment : Fragment() {
    private lateinit var notesViewModel: NotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_note, container, false)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        root.addNoteButton.setOnClickListener {
            insertDataToDatabase()
        }

        return root
    }

    private fun insertDataToDatabase() {
        val car = addNoteCar.text.toString()
        val noteDescription = addNoteText.text.toString()

        if (inputCheck(car, noteDescription)) {
            val note = NoteEntity(0, car, noteDescription)
            notesViewModel.addNote(note)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addNote_to_nav_notes)
        } else {
            Toast.makeText(requireContext(), "Complete all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(car: String, note: String): Boolean {
        return !(TextUtils.isEmpty(car) || TextUtils.isEmpty(note))
    }

}