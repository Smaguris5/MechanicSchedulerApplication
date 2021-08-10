package com.ar1246.mechanicschedulerapplication.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ar1246.mechanicschedulerapplication.data.db.CarEntity
import com.ar1246.mechanicschedulerapplication.data.db.CustomerEntity
//One to many relationship
data class CustomerWithCars(
    @Embedded val customer: CustomerEntity,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "owner"
    )
    val cars: List<CarEntity>
)