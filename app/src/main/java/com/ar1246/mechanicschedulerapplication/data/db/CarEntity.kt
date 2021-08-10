package com.ar1246.mechanicschedulerapplication.data.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "car_table")
data class CarEntity(
    @PrimaryKey(autoGenerate = false)
    val licencePlate: String,
    val make: String,
    val model: String,
    val year: Int,
    val owner: Int
) : Parcelable