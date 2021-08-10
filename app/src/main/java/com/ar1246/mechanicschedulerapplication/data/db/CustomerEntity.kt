package com.ar1246.mechanicschedulerapplication.data.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "customer_table")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val mobileNumber: String
) : Parcelable