package org.vmaslov.budget.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class BudgetDatabaseTest {

    lateinit var db: BudgetDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BudgetDatabase::class.java).build()
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun accountDao_getInsertDelete() {
        val account = createAccount()
        val accountDao = db.accountDao()
        accountDao.insert(account)
        var result = accountDao.findAll()
        assertEquals("Returned number of entities is incorrect", 1, result.size)
        assertEquals(1L, result.get(0).uid)
        assertEquals("Foo", result.get(0).name)
        assertEquals(false, result.get(0).isPassive)
        accountDao.delete(result.get(0))
        result = accountDao.findAll()
        assertEquals("Returned number of entities is incorrect", 0, result.size)
    }

    @Test
    fun accountDao_getInsertDeleteByUid() {
        val account = createAccount()
        val accountDao = db.accountDao()
        accountDao.insert(account)
        var result = accountDao.findAll()
        assertEquals("Returned number of entities is incorrect", 1, result.size)
        assertEquals(1L, result.get(0).uid)
        assertEquals("Foo", result.get(0).name)
        assertEquals(false, result.get(0).isPassive)
        accountDao.deleteByUid(result.get(0).uid!!)
        result = accountDao.findAll()
        assertEquals("Returned number of entities is incorrect", 0, result.size)
    }

    @Test
    fun accountDao_update() {
        val account = createAccount()
        val accountDao = db.accountDao()
        accountDao.insert(account)
        val result = accountDao.findAll().get(0)
        result.name = "Bar"
        result.isPassive = true
        accountDao.update(result)
        val updated = accountDao.findAll().get(0)
        assertEquals("Bar", updated.name)
        assertEquals(true, updated.isPassive)
    }

    @Test
    fun expenseTypeDao_getInsertDelete() {
        val expenseType = createExpenseType()
        val dao = db.expenseTypeDao()
        dao.insert(expenseType)
        var result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(1L, result.get(0).uid)
        assertEquals("Foo", result.get(0).name)
        assertEquals(BigDecimal.ZERO, result.get(0).budgetAmount)
        dao.delete(result.get(0))
        result = dao.findAll()
        assertEquals(0, result.size)
    }

    @Test
    fun expenseTypeDao_update() {
        val entity = createExpenseType()
        val dao = db.expenseTypeDao()
        dao.insert(entity)
        val result = dao.findAll().get(0)
        result.name = "Bar"
        result.budgetAmount = BigDecimal(123)
        dao.update(result)
        val updated = dao.findAll().get(0)
        assertEquals("Bar", updated.name)
        assertEquals(BigDecimal(123), updated.budgetAmount)
    }

    @Test
    fun operationDao_getInsertDelete() {
        val entity = createOperation(db)
        val dao = db.operationDao()
        dao.insert(entity)
        var result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(1L, result.get(0).uid)
        assertEquals(1L, result.get(0).account)
        assertEquals(1L, result.get(0).type)
        assertEquals(BigDecimal.ONE, result.get(0).amount)
        assertEquals(Timestamp(2020), result.get(0).createdAt)
        assertNull(result.get(0).comment)
        dao.delete(result.get(0))
        result = dao.findAll()
        assertEquals(0, result.size)
    }
}

fun createOperation(db: BudgetDatabase): OperationEntity {
    db.accountDao().insert(createAccount())
    db.expenseTypeDao().insert(createExpenseType())
    return OperationEntity(
        account = 1L,
        type = 1L,
        amount = BigDecimal.ONE,
        createdAt = Timestamp(2020)
    )
}

fun createExpenseType(): ExpenseTypeEntity {
    return ExpenseTypeEntity(name = "Foo")
}

fun createAccount(): AccountEntity {
    return AccountEntity(name = "Foo")
}