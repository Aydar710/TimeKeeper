package ru.timekeeper

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import ru.timekeeper.di.component.AppComponent
import ru.timekeeper.di.component.DaggerAppComponent
import ru.timekeeper.di.module.AppModule

class App : Application() {

    companion object {
        @Suppress("LateinitUsage")
        lateinit var component: AppComponent
        var idsCollection : CollectionReference? = null
    }

    override fun onCreate() {
        super.onCreate()

        val db = FirebaseFirestore.getInstance()
        idsCollection = db.collection(FIREBASE_COLLECTION_NAME_IDS)

        Stetho.initializeWithDefaults(this)

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        //component.inject(this)
    }
}
