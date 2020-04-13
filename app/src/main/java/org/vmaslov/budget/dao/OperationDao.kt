package org.vmaslov.budget.dao

import androidx.room.*

@Dao
interface OperationDao {

    @Insert
    fun insert(input: OperationEntity)

    @Delete
    fun delete(input: OperationEntity)

    @Query("SELECT * FROM operations")
    fun findAll(): List<OperationEntity>
}
