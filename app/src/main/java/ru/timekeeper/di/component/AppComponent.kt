package ru.timekeeper.di.component

import android.content.Context
import dagger.Component
import ru.timekeeper.data.di.module.NetModule
import ru.timekeeper.data.di.module.RepositoryModule
import ru.timekeeper.data.di.module.ServiceModule
import ru.timekeeper.di.module.AppModule
import ru.timekeeper.ui.vk.UserGroupsFragment
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

    fun inject(userGroupsFragment: UserGroupsFragment)
}