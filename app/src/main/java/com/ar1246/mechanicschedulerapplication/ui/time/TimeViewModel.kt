package com.ar1246.mechanicschedulerapplication.ui.time

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ar1246.mechanicschedulerapplication.data.db.MechanicAppDatabase
import com.ar1246.mechanicschedulerapplication.data.db.MechanicAppRepository
import com.ar1246.mechanicschedulerapplication.data.db.StatementEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<StatementEntity>>
    private val repository: MechanicAppRepository

    init {
        val timeDao = MechanicAppDatabase.getDatabase(application).mechanicAppDao()
        repository = MechanicAppRepository(timeDao)
        readAllData = repository.readAllStatementData
    }

    fun addStatement(statement: StatementEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStatement(statement)
        }
    }
}