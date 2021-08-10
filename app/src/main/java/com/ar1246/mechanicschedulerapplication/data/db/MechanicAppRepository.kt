package com.ar1246.mechanicschedulerapplication.data.db

import androidx.lifecycle.LiveData

class MechanicAppRepository(private val mechanicAppDao: MechanicAppDao) {
    val readAllNoteData: LiveData<List<NoteEntity>> = mechanicAppDao.readAllNoteData()
    val readAllStatementData: LiveData<List<StatementEntity>> =
        mechanicAppDao.readAllStatementData()
    val readAllEventData: LiveData<List<EventEntity>> = mechanicAppDao.readAllEventData()

    suspend fun addNote(note: NoteEntity) {
        mechanicAppDao.addNote(note)
    }

    suspend fun addStatement(statement: StatementEntity) {
        mechanicAppDao.addStatement(statement)
    }

    suspend fun updateNote(note: NoteEntity) {
        mechanicAppDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        mechanicAppDao.deleteNote(note)
    }
}