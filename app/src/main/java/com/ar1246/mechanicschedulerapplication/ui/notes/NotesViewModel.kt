package com.ar1246.mechanicschedulerapplication.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ar1246.mechanicschedulerapplication.data.db.MechanicAppDatabase
import com.ar1246.mechanicschedulerapplication.data.db.MechanicAppRepository
import com.ar1246.mechanicschedulerapplication.data.db.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<NoteEntity>>
    private val repository: MechanicAppRepository

    init {
        val noteDao = MechanicAppDatabase.getDatabase(application).mechanicAppDao()
        repository = MechanicAppRepository(noteDao)
        readAllData = repository.readAllNoteData
    }

    fun addNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
}