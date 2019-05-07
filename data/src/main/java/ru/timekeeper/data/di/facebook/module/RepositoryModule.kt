package ru.timekeeper.data.di.facebook.module

import dagger.Module
import dagger.Provides
import ru.timekeeper.data.repository.FacebookRepository
import ru.timekeeper.data.service.FacebookService
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideVkRepository(facebookService: FacebookService): FacebookRepository=
        FacebookRepository(facebookService)
}
