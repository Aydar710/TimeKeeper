package ru.timekeeper.data.di.vkontakte.module

import dagger.Module
import dagger.Provides
import ru.timekeeper.data.repository.VkRepository
import ru.timekeeper.data.service.VkService
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideVkRepository(vkService: VkService): VkRepository =
        VkRepository(vkService)
}
