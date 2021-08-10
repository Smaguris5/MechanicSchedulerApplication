package com.ar1246.mechanicschedulerapplication.ui.noteUpdate

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.db.NoteEntity
import com.ar1246.mechanicschedulerapplication.ui.notes.NotesViewModel
import kotlinx.android.synthetic.main.fragment_update_note.*
import kotlinx.android.synthetic.main.fragment_update_note.view.*

class UpdateNoteFragment : Fragment() {

    private val args by navArgs<UpdateNoteFragmentArgs>()
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_update_note, container, false)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        root.updateNoteCar.setText(args.currentUser.car)
        root.updateNoteText.setText(args.currentUser.description)

        root.updateNoteButton.setOnClickListener {
            updateItem()
        }

        //adds delete menu
        setHasOptionsMenu(true)

        return root
    }

    private fun updateItem() {
        val car = updateNoteCar.text.toString()
        val text = updateNoteText.text.toString()
        if (inputCheck(car, text)) {
            //create note object
            val updatedNote = NoteEntity(args.currentUser.noteId, car, text)
            //update current note
            notesViewModel.updateNote(updatedNote)

            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_nav_notes)
        } else {
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(car: String, note: String): Boolean {
        return !(TextUtils.isEmpty(car) || TextUtils.isEmpty(note))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            notesViewModel.deleteNote(args.currentUser)
            Toast.makeText(requireContext(), "Note removed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_nav_notes)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete note?")
        builder.setMessage("Are you sure you want to delete the note regarding ${args.currentUser.car}?")
        builder.create().show()
    }
}