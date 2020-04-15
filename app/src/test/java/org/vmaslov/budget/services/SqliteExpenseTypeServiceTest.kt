package org.vmaslov.budget.services

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.vmaslov.budget.dao.ExpenseTypeDao
import org.vmaslov.budget.dao.ExpenseTypeEntity

class SqliteExpenseTypeServiceTest {

    @RelaxedMockK
    lateinit var dao: ExpenseTypeDao

    @InjectMockKs
    lateinit var service: SqliteExpenseTypeService

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun save_noUid() {
        val expenseTypeEntity = ExpenseTypeEntity(name = "Foo")

        service.save(expenseTypeEntity)

        verify { dao.insert(expenseTypeEntity) }
        verify(exactly = 0) { dao.update(any()) }
    }

    @Test
    fun save_uidSet() {
        val expenseTypeEntity = ExpenseTypeEntity(1L, name = "Foo")

        service.save(expenseTypeEntity)

        verify { dao.update(expenseTypeEntity) }
        verify(exactly = 0) { dao.insert(any()) }
    }

    @Test
    fun delete() {
        service.delete(1L)

        verify { dao.deleteByUid(1L) }
    }
}