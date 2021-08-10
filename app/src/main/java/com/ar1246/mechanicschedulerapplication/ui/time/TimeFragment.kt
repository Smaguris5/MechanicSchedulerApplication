package com.ar1246.mechanicschedulerapplication.ui.time

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar1246.mechanicschedulerapplication.R
import com.ar1246.mechanicschedulerapplication.data.db.StatementEntity
import kotlinx.android.synthetic.main.fragment_time.view.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit


class TimeFragment : Fragment() {
    private lateinit var viewModel: TimeViewModel
    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TimeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_time, container, false)
        val recyclerView = root.recyclerview
        val adapter = TimeAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { time ->
            adapter.setData(time)
        })

        var startTime: LocalDateTime = LocalDateTime.now()
        var endTime: LocalDateTime
        var hourlyRate: Double = 8.00
        val chronometer: Chronometer = root.timer_current_job
        val startButton: Button = root.button_clock_in

        startButton.setOnClickListener {
            if (!isPlaying) {
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                isPlaying = true
                startTime = LocalDateTime.now()
                startButton.text = "Clock Out"

            } else {
                chronometer.stop()
                isPlaying = false
                endTime = LocalDateTime.now()
                val startDate = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant())
                val endDate = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant())
                val elapsedMillis: Long = SystemClock.elapsedRealtime() - chronometer.base
                startButton.text = "Clock In"

                //Get the time elapsed in hours minutes and seconds
                val hours = TimeUnit.MILLISECONDS.toHours(elapsedMillis)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedMillis)
                var elapsedTime: String = elapsedMillis.toString()

                //Variable set to prevent accidental statements. If the job is shorter than 10 seconds, it will not be recorded to the database
                var isLongEnough = true
                //Setting time elapsed as a string and adding 00: where applicable for uniform format
                when {
                    hours > 0 -> {
                        elapsedTime = String.format(
                            "%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(elapsedMillis),
                            TimeUnit.MILLISECONDS.toMinutes(elapsedMillis) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(elapsedMillis)
                            ),
                            TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)
                            )
                        )
                    }
                    minutes > 0 -> {
                        elapsedTime = String.format(
                            "00:%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(elapsedMillis),
                            TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)
                            )
                        )
                    }
                    seconds > 10 -> {
                        elapsedTime = String.format(
                            "00:00:%02d",
                            TimeUnit.MILLISECONDS.toSeconds(elapsedMillis)
                        )
                    }
                    else -> {
                        isLongEnough = false
                        Toast.makeText(requireContext(), "Job too short", Toast.LENGTH_SHORT).show()
                    }
                }
                if (isLongEnough) {
                    val statement = StatementEntity(0, startDate, endDate, elapsedTime, hourlyRate)
                    viewModel.addStatement(statement)
                    Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
                }
            }
        }

        return root
    }
}