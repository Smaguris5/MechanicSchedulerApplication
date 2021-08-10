package com.ar1246.mechanicschedulerapplication.ui.reminders

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.ReminderService
import kotlinx.android.synthetic.main.fragment_reminders.view.*
import java.util.*

class RemindersFragment : Fragment() {

    lateinit var reminderService: ReminderService
    private lateinit var remindersViewModel: RemindersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        remindersViewModel =
            ViewModelProvider(this).get(RemindersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reminders, container, false)

        reminderService = ReminderService(context as Activity)

        root.setExact.setOnClickListener {
            setAlarm { reminderService.setExactAlarm(it) }
        }

        root.setRepetitive.setOnClickListener { setAlarm { reminderService.setRepetitiveAlarm(it) } }

        return root
    }

    private fun setAlarm(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            DatePickerDialog(
                context as Activity,
                0,
                { _, year, month, day ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)
                    TimePickerDialog(
                        context as Activity,
                        0,
                        { _, hour, minute ->
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            this.set(Calendar.MINUTE, minute)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}