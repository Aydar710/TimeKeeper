package ru.timekeeper.data.di.module

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import ru.timekeeper.data.FIREBASE_COLLECTION_NAME_IDS
import javax.inject.Singleton

@Module
class FirestoreModule {

    @Singleton
    @Provides
    fun provideCollectionReference(): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        return db.collection(FIREBASE_COLLECTION_NAME_IDS)
    }
}
