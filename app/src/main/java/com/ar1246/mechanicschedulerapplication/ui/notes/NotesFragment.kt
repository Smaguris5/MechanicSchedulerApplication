package com.ar1246.mechanicschedulerapplication.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar1246.mechanicschedulerapplication.R
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesFragment : Fragment() {
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        val recyclerView = root.recyclerview
        val adapter = NotesAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        notesViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        root.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_notes_to_addNote)
        }

        return root
    }
}