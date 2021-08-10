package com.ar1246.mechanicschedulerapplication.ui.calendar

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar1246.mechanicschedulerapplication.R
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import okhttp3.*
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment : Fragment() {
    private lateinit var calendarViewModel: CalendarViewModel


    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendarViewModel =
            ViewModelProvider(this).get(CalendarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        val calendar: CompactCalendarView = root.compactcalendar_view
        getHolidays(calendar)
        val recyclerView = root.recyclerview
        val adapter = CalendarEventAdapter()

        val df: DateFormat = SimpleDateFormat("MMM, yyyy")
        var currDate = df.format(calendar.firstDayOfCurrentMonth)
        root.month_title_text.text = currDate.toString()


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        calendarViewModel.currentDate.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { event ->
                adapter.setData(event)
            })



        root.previous_button.setOnClickListener {
            calendar.scrollLeft()
        }
        root.next_button.setOnClickListener {
            calendar.scrollRight()
        }

        calendar.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                //    val events: List<Event> = calendar.getEvents(dateClicked)
                //    calendarViewModel.currentDate.value = events
                //    Log.d(TAG, "Day was clicked: $dateClicked with events $events")
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                currDate = df.format(firstDayOfNewMonth)
                root.month_title_text.text = currDate.toString()

                var events = calendar.getEventsForMonth(firstDayOfNewMonth)
                calendarViewModel.currentDate.value = events
            }
        })

        return root
    }

    private fun getHolidays(calendar: CompactCalendarView) {
        val url = "https://www.gov.uk/bank-holidays.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        //Fetch Json holiday API using enqueue (async task)
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val events = gson.fromJson(body, CalendarWhole::class.java)

                //Load public holidays onto the calendar
                for (event: CalendarEvent in events.englandAndWhales.events) {
                    var description = event.title
                    var time: Long = event.date.time

                    calendar.addEvent(Event(Color.parseColor("#AA8D6D"), time, description))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(
                    requireContext(),
                    "Failed to load public holidays",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}