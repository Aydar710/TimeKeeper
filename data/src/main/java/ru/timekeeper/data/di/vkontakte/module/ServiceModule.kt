package ru.timekeeper.data.di.vkontakte.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.timekeeper.data.service.VkService
import javax.inject.Singleton

@Module(includes = [NetModule::class])
class ServiceModule {

    @Singleton
    @Provides
    fun provideVkService(retrofit: Retrofit): VkService =
        retrofit.create(VkService::class.java)
}
