package ru.timekeeper.data.di.module

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import ru.timekeeper.data.repository.SharedPrefWrapper
import javax.inject.Singleton

@Module(includes = [SharedPrefModule::class])
class FirestoreModule {

    @Singleton
    @Provides
    fun provideCollectionReference(sPref : SharedPrefWrapper): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection(sPref.getUserId().toString())
    }
}
