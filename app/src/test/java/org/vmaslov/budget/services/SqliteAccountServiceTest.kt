package org.vmaslov.budget.services

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.vmaslov.budget.dao.AccountDao
import org.vmaslov.budget.dao.AccountEntity

class SqliteAccountServiceTest {

    @RelaxedMockK
    lateinit var dao: AccountDao

    @InjectMockKs
    lateinit var service: SqliteAccountService

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun save_shouldInsertWhenUidNull() {
        val accountEntity = AccountEntity(name = "Foo")
        every { dao.insert(accountEntity) } returns Unit

        service.save(accountEntity)

        verify {
            dao.insert(accountEntity)
        }
        verify(exactly = 0) {
            dao.update(any())
        }
    }

    @Test
    fun save_shouldInsertWhenUidSet() {
        val accountEntity = AccountEntity(uid = 1L, name = "Foo")

        service.save(accountEntity)

        verify {
            dao.update(accountEntity)
        }
        verify(exactly = 0) {
            dao.insert(any())
        }
    }

    @Test
    fun delete() {
        service.delete(1L)

        verify { dao.deleteByUid(1L) }
    }
}