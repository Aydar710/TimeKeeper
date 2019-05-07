package ru.timekeeper.vkontakte.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.timekeeper.common.ViewModelFactory
import ru.timekeeper.common.ViewModelKey
import ru.timekeeper.vkontakte.viewModels.VkGroupWallViewModel
import ru.timekeeper.vkontakte.viewModels.VkGroupsFragmentViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VkGroupsFragmentViewModel::class)
    internal abstract fun bindVkUserGroupsFragmentViewModel(
            viewModel: VkGroupsFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VkGroupWallViewModel::class)
    internal abstract fun bindVkGroupWallViewModel(viewModel: VkGroupWallViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(
            factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
