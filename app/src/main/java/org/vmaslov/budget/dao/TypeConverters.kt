package org.vmaslov.budget.dao

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.sql.Timestamp

class BigDecimalTypeConverter {

    @TypeConverter
    fun bigDecimalToString(input: BigDecimal?): String = input?.toPlainString() ?: ""

    @TypeConverter
    fun stringToBigDecimal(input: String?): BigDecimal {
        if (input.isNullOrBlank()) return BigDecimal.valueOf(0.0)
        return input.toBigDecimalOrNull() ?: BigDecimal.valueOf(0.0)
    }

}

class TimestampTypeConverter {

    @TypeConverter
    fun timestampToLong(input: Timestamp?): Long = input?.time ?: 0

    @TypeConverter
    fun longToTimestamp(input: Long?): Timestamp = Timestamp(input ?: 0)
}