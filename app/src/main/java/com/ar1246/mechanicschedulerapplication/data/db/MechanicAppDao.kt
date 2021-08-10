package com.ar1246.mechanicschedulerapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ar1246.mechanicschedulerapplication.data.db.relations.CustomerWithCars

@Dao
interface MechanicAppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCustomer(customer: CustomerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(car: CarEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStatement(statement: StatementEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Update
    suspend fun updateCustomer(customer: CustomerEntity)

    @Update
    suspend fun updateCar(car: CarEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Delete
    suspend fun deleteCustomer(customer: CustomerEntity)

    @Delete
    suspend fun deleteCar(car: CarEntity)

    @Query("SELECT * FROM customer_table ORDER BY customerId ASC")
    fun readAllCustomerData(): LiveData<List<CustomerEntity>>

    @Query("SELECT * FROM note_table ORDER BY noteId ASC")
    fun readAllNoteData(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM car_table ORDER BY licencePlate ASC")
    fun readAllCarData(): LiveData<List<CarEntity>>

    @Query("SELECT * FROM statement_table ORDER BY statementId ASC")
    fun readAllStatementData(): LiveData<List<StatementEntity>>

    @Query("SELECT * FROM event_table ORDER BY eventId ASC")
    fun readAllEventData(): LiveData<List<EventEntity>>

    @Transaction
    @Query("SELECT * FROM customer_table WHERE customerId=:customerId ORDER BY customerId ASC")
    suspend fun getCustomerWithCars(customerId: String): List<CustomerWithCars>
}