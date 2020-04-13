package org.vmaslov.budget.dao

import androidx.room.*

@Dao
interface ExpenseTypeDao {

    @Insert
    fun insert(input: ExpenseTypeEntity)

    @Update
    fun update(input: ExpenseTypeEntity)

    @Delete
    fun delete(input: ExpenseTypeEntity)

    @Query("SELECT * FROM expense_types")
    fun findAll(): List<ExpenseTypeEntity>

}
