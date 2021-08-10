package com.ar1246.mechanicschedulerapplication.data.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "statement_table")
data class StatementEntity(
    @PrimaryKey(autoGenerate = true)
    val statementId: Int,
    val startTime: Date,
    val endTime: Date,
    val timeElapsed: String,
    val hourlyRate: Double
) : Parcelable