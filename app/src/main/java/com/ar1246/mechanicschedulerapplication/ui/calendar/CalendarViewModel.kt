package com.ar1246.mechanicschedulerapplication.ui.calendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.sundeepk.compactcalendarview.domain.Event

class CalendarViewModel(application: Application) : AndroidViewModel(application) {
    val currentDate: MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>()
    }
}