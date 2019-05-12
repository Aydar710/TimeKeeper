package ru.timekeeper.data.di.module

import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import ru.timekeeper.data.repository.VkRepository
import ru.timekeeper.data.service.VkService
import javax.inject.Singleton

@Module(includes = [ServiceModule::class, FirestoreModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideVkRepository(vkService: VkService, collectionReference: CollectionReference): VkRepository =
        VkRepository(vkService, collectionReference)
}
