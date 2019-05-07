package ru.timekeeper.vkontakte.di.component

import android.content.Context
import dagger.Component
import ru.timekeeper.common.ui.LoginActivity
import ru.timekeeper.data.di.vkontakte.module.NetModule
import ru.timekeeper.data.di.vkontakte.module.RepositoryModule
import ru.timekeeper.data.di.vkontakte.module.ServiceModule
import ru.timekeeper.facebook.ui.FacebookGroupListFragment
import ru.timekeeper.vkontakte.di.module.AppModule
import ru.timekeeper.vkontakte.di.module.SharedPrefModule
import ru.timekeeper.vkontakte.di.module.ViewModelModule
import ru.timekeeper.vkontakte.ui.VkGroupWallFragment
import ru.timekeeper.vkontakte.ui.VkGroupListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        SharedPrefModule::class,
        ru.timekeeper.data.di.facebook.module.RepositoryModule::class
    ]
)
interface AppComponent {

    fun provideApp(): Context

    fun inject(userGroupsFragment: VkGroupListFragment)
    fun inject(vkGroupWallFragment: VkGroupWallFragment)
    fun inject(loginActivity: LoginActivity)

    fun inject(facebookGroupListFragment : FacebookGroupListFragment)
}
