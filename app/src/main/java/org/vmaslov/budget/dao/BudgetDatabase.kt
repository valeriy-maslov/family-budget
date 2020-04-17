package org.vmaslov.budget.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Budget app database object.
 */
@Database(
    entities = [AccountEntity::class, ExpenseTypeEntity::class, OperationEntity::class],
    version = 1
)
@TypeConverters(BigDecimalTypeConverter::class, TimestampTypeConverter::class)
abstract class BudgetDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun expenseTypeDao(): ExpenseTypeDao
    abstract fun operationDao(): OperationDao
}

const val DATABASE_NAME : String = "vm_family_budget"
