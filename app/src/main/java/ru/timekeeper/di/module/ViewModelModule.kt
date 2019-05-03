package ru.timekeeper.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.timekeeper.ViewModelFactory
import ru.timekeeper.ViewModelKey
import ru.timekeeper.viewModels.VkGroupWallViewModel
import ru.timekeeper.viewModels.VkGroupsFragmentViewModel

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
            factory: ViewModelFactory): ViewModelProvider.Factory
}
