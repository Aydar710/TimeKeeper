package ru.timekeeper.data.di.module

import dagger.Binds
import dagger.Module
import ru.timekeeper.core.interfaces.VkRepository
import ru.timekeeper.data.repository.VkRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindVkRepository(vkRepositoryImpl: VkRepositoryImpl): VkRepository
}