package ru.timekeeper.vkontakte.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.timekeeper.common.SharedPrefWrapper
import javax.inject.Singleton

@Module
class SharedPrefModule {

    @Singleton
    @Provides
    fun provideSharedPrefWrapper(context: Context): SharedPrefWrapper =
        SharedPrefWrapper(context)
}
