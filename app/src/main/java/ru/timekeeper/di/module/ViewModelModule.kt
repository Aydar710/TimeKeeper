package ru.timekeeper.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.timekeeper.ViewModelFactory
import ru.timekeeper.ViewModelKey
import ru.timekeeper.viewModels.CombinedFeedViewModel
import ru.timekeeper.viewModels.VkGroupWallViewModel
import ru.timekeeper.viewModels.VkGroupsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VkGroupsViewModel::class)
    internal abstract fun bindVkUserGroupsFragmentViewModel(
        viewModel: VkGroupsViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VkGroupWallViewModel::class)
    internal abstract fun bindVkGroupWallViewModel(viewModel: VkGroupWallViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CombinedFeedViewModel::class)
    internal abstract fun bindCombinedFeedViewModel(viewModel: CombinedFeedViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
