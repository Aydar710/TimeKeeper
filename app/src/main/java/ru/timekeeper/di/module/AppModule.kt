package ru.timekeeper.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.timekeeper.App
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provieApp(): Context = app
}
