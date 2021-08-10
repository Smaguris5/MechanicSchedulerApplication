package com.ar1246.mechanicschedulerapplication.ui.statements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar1246.mechanicschedulerapplication.R
import kotlinx.android.synthetic.main.fragment_notes.view.*

class StatementsFragment : Fragment() {

    private lateinit var statementsViewModel: StatementsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statementsViewModel =
            ViewModelProvider(this).get(StatementsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statements, container, false)
        val recyclerView = root.recyclerview
        val adapter = StatementsAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        statementsViewModel.readAllData.observe(viewLifecycleOwner, Observer { statement ->
            adapter.setData(statement)
        })

        return root
    }
}