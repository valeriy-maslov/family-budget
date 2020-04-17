package org.vmaslov.budget.di

import dagger.Component
import org.vmaslov.budget.services.AccountService
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ServiceModule::class])
interface ServiceFactory {

    fun accountService(): AccountService
}