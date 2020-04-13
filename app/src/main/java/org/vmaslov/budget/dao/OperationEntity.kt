package org.vmaslov.budget.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.sql.Timestamp

/**
 * Represents the single account operation.
 */
@Entity(
    tableName = "operations",
    foreignKeys = [ForeignKey(
        entity = AccountEntity::class,
        parentColumns = ["uid"],
        childColumns = ["account"]
    ), ForeignKey(
        entity = ExpenseTypeEntity::class,
        parentColumns = ["uid"],
        childColumns = ["type"]
    )]
)
data class OperationEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long? = null,
    @ColumnInfo(name = "account", index = true) val account: Long,
    @ColumnInfo(name = "type", index = true) val type: Long = 0,
    @ColumnInfo(name = "amount") val amount: BigDecimal,
    @ColumnInfo(name = "created_at", index = true) val createdAt: Timestamp,
    @ColumnInfo(name = "comment") val comment: String? = null
)