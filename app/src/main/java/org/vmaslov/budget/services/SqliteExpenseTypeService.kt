package org.vmaslov.budget.services

import org.vmaslov.budget.dao.ExpenseTypeDao
import org.vmaslov.budget.dao.ExpenseTypeEntity
import java.util.Objects.isNull
import javax.inject.Inject

/**
 * [ExpenseTypeService] implementation based on SQLite (uses Room under the hood).
 *
 * @see [ExpenseTypeEntity]
 */
class SqliteExpenseTypeService @Inject constructor(private var dao: ExpenseTypeDao) :
    ExpenseTypeService {

    override fun save(input: ExpenseTypeEntity) {
        if (isNull(input.uid)) {
            dao.insert(input)
        } else {
            dao.update(input)
        }
    }

    override fun delete(uid: Long) {
        dao.deleteByUid(uid)
    }

    override fun getAll(): List<ExpenseTypeEntity> = dao.findAll()
}