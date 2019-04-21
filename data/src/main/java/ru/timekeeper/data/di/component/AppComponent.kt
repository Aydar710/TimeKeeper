package ru.timekeeper.data.di.component

import android.content.Context
import dagger.Component
import ru.timekeeper.data.di.module.AppModule
import ru.timekeeper.data.di.module.NetModule
import ru.timekeeper.data.di.module.RepositoryModule
import ru.timekeeper.data.di.module.ServiceModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        ServiceModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun provideApp() : Context


}