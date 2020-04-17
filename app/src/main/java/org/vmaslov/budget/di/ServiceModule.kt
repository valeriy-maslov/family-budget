package org.vmaslov.budget.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.vmaslov.budget.dao.AccountDao
import org.vmaslov.budget.dao.BudgetDatabase
import org.vmaslov.budget.dao.DATABASE_NAME
import org.vmaslov.budget.services.AccountService
import org.vmaslov.budget.services.SqliteAccountService
import javax.inject.Singleton

@Module
class ServiceModule(application: Application) {

    val database: BudgetDatabase

    init {
        database =
            Room.databaseBuilder(application, BudgetDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun database(): BudgetDatabase = database

    @Provides
    @Singleton
    fun accountDao(db: BudgetDatabase): AccountDao = db.accountDao()

    @Provides
    @Singleton
    fun accountService(dao: AccountDao): AccountService = SqliteAccountService(dao)
}