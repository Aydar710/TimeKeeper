package ru.timekeeper.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.timekeeper.ViewModelFactory
import ru.timekeeper.ViewModelKey
import ru.timekeeper.viewModels.UserGroupsFragmentViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserGroupsFragmentViewModel::class)
    internal abstract fun demoViewModel(viewModel: UserGroupsFragmentViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(
            factory: ViewModelFactory): ViewModelProvider.Factory
}