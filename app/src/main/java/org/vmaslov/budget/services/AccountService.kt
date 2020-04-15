package org.vmaslov.budget.services

import org.vmaslov.budget.dao.AccountEntity

/**
 * Provides business-level methods to control accounts.
 */
interface AccountService {

    /**
     * Saves the given [AccountEntity].
     */
    fun save(input: AccountEntity)

    /**
     * Removes [AccountEntity] with the given UID.
     *
     * @see [AccountEntity.uid]
     */
    fun delete(uid: Long)
}