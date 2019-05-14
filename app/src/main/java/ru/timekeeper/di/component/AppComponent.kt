package ru.timekeeper.di.component

import android.content.Context
import dagger.Component
import ru.timekeeper.data.di.module.NetModule
import ru.timekeeper.data.di.module.RepositoryModule
import ru.timekeeper.data.di.module.ServiceModule
import ru.timekeeper.di.module.AppModule
import ru.timekeeper.di.module.SharedPrefModule
import ru.timekeeper.di.module.ViewModelModule
import ru.timekeeper.ui.vk.CombinedFeedFragment
import ru.timekeeper.ui.vk.LoginActivity
import ru.timekeeper.ui.vk.VkGroupWallFragment
import ru.timekeeper.ui.vk.VkGroupsFragment
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            NetModule::class,
            ServiceModule::class,
            RepositoryModule::class,
            ViewModelModule::class,
            SharedPrefModule::class
        ]
)
interface AppComponent {

    fun provideApp(): Context

    fun inject(userGroupsFragment: VkGroupsFragment)
    fun inject(vkGroupWallFragment: VkGroupWallFragment)
    fun inject(loginActivity: LoginActivity)
    fun inject(combinedFeedFragment: CombinedFeedFragment)
}
