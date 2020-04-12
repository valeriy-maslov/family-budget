package org.vmaslov.budget.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.math.BigDecimal

/**
 * Represents the type of expense. Can have a budget which is the planned amount.
 */
@Entity(tableName = "expense_types")
data class ExpenseTypeEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "budget_amount") var budgetAmount: BigDecimal = BigDecimal.ZERO
)