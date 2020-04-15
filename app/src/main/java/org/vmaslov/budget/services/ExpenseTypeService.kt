package org.vmaslov.budget.services

import org.vmaslov.budget.dao.ExpenseTypeEntity

/**
 * Provides business-level methods for controlling expense types.
 */
interface ExpenseTypeService {

    fun save(input: ExpenseTypeEntity)

    fun delete(uid: Long)
}