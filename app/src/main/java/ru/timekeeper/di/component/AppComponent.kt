package ru.timekeeper.di.component

import android.content.Context
import dagger.Component
import ru.timekeeper.App
import ru.timekeeper.data.di.module.NetModule
import ru.timekeeper.data.di.module.RepositoryModule
import ru.timekeeper.data.di.module.ServiceModule
import ru.timekeeper.data.di.module.SharedPrefModule
import ru.timekeeper.di.module.AppModule
import ru.timekeeper.di.module.ViewModelModule
import ru.timekeeper.ui.vk.*
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
    fun inject(mainActivity: MainActivity)
    fun inject(combinedFeedFragment: CombinedFeedFragment)
    fun inject(app : App)
}
