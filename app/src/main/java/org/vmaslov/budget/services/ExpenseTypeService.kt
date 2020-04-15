package org.vmaslov.budget.services

import org.vmaslov.budget.dao.ExpenseTypeEntity

/**
 * Provides business-level methods for controlling expense types.
 */
interface ExpenseTypeService {

    /**
     * Saves the given expense type.
     */
    fun save(input: ExpenseTypeEntity)

    /**
     * Removes the expense type by the given UID.
     *
     * @see [ExpenseTypeEntity.uid]
     */
    fun delete(uid: Long)

    /**
     * @return List of [ExpenseTypeEntity] existing in the system.
     */
    fun getAll() : List<ExpenseTypeEntity>
}