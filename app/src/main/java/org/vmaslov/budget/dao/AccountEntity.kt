package org.vmaslov.budget.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents the account.
 *
 * <p>Can be active or passive. In accounting active accounts are debit accounts and passive -
 * credit accounts.
 */
@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "is_passive") var isPassive: Boolean = false
)