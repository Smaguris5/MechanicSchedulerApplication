package com.ar1246.mechanicschedulerapplication.ui.statements

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ar1246.mechanicschedulerapplication.data.db.MechanicAppDatabase
import com.ar1246.mechanicschedulerapplication.data.db.MechanicAppRepository
import com.ar1246.mechanicschedulerapplication.data.db.StatementEntity

class StatementsViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<StatementEntity>>
    private val repository: MechanicAppRepository

    init {
        val noteDao = MechanicAppDatabase.getDatabase(application).mechanicAppDao()
        repository = MechanicAppRepository(noteDao)
        readAllData = repository.readAllStatementData
    }

}