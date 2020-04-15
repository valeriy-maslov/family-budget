package org.vmaslov.budget.services

import org.vmaslov.budget.dao.AccountDao
import org.vmaslov.budget.dao.AccountEntity
import java.util.*
import javax.inject.Inject

/**
 * [AccountService] implementation based on SQLite (uses Room under the hood).
 *
 * @see [AccountService]
 * @see [AccountDao]
 */
class SqliteAccountService @Inject constructor(private val dao: AccountDao) : AccountService {

    override fun save(input: AccountEntity) {
        if (Objects.isNull(input.uid)) {
            dao.insert(input)
        } else {
            dao.update(input)
        }
    }

    override fun delete(uid: Long) {
        dao.deleteByUid(uid)
    }
}