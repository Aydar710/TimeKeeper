package ru.timekeeper.di.component

import android.content.Context
import dagger.Component
import ru.timekeeper.data.di.module.NetModule
import ru.timekeeper.data.di.module.RepositoryModule
import ru.timekeeper.data.di.module.ServiceModule
import ru.timekeeper.di.module.AppModule
import ru.timekeeper.di.module.ViewModelModule
import ru.timekeeper.ui.vk.UserGroupsFragment
import ru.timekeeper.ui.vk.VkGroupWallFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun provideApp(): Context

    fun inject(userGroupsFragment: UserGroupsFragment)
    fun inject(vkGroupWallFragment: VkGroupWallFragment)
}