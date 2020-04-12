package org.vmaslov.budget.dao

import androidx.room.*

/**
 * [AccountEntity] data-access object.
 */
@Dao
interface AccountDao {
    @Insert
    fun insert(account: AccountEntity)

    @Delete
    fun delete(account: AccountEntity)

    @Query("SELECT * FROM accounts")
    fun findAll(): List<AccountEntity>

    @Update
    fun update(result: AccountEntity)
}