package ru.timekeeper.data.di.facebook.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.timekeeper.data.NAME_FACEBOOK_RETROFIT
import ru.timekeeper.data.service.FacebookService
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetModule::class])
class ServiceModule {

    @Singleton
    @Provides
    fun provideFacebookService(@Named(NAME_FACEBOOK_RETROFIT) retrofit: Retrofit): FacebookService =
        retrofit.create(FacebookService::class.java)
}
