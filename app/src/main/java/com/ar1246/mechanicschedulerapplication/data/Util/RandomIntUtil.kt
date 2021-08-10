package com.ar1246.mechanicschedulerapplication.data.Util

import java.util.concurrent.atomic.AtomicInteger

//Object that generates random integer for reminders
object RandomIntUtil {
    private val seed = AtomicInteger()
    fun getRandomInt() = seed.getAndIncrement() + System.currentTimeMillis().toInt()
}