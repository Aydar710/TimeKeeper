package ru.timekeeper.di

import dagger.Component
import ru.timekeeper.App
import ru.timekeeper.data.di.module.RepositoryModule
import ru.timekeeper.data.di.module.ServiceModule
import javax.inject.Singleton

@Component(
    modules = [
    AppModule::class,
    ServiceModule::class,
    RepositoryModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(app : App)
}
