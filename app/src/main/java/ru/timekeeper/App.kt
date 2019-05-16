package ru.timekeeper

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import ru.timekeeper.data.repository.SharedPrefWrapper
import ru.timekeeper.di.component.AppComponent
import ru.timekeeper.di.component.DaggerAppComponent
import ru.timekeeper.di.module.AppModule
import javax.inject.Inject

class App : Application() {

    companion object {
        @Suppress("LateinitUsage")
        lateinit var component: AppComponent
        var idsCollection: CollectionReference? = null
    }

    private lateinit var tokenTracker: VKTokenExpiredHandler

    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun onCreate() {
        super.onCreate()
        VK.initialize(this)
        val db = FirebaseFirestore.getInstance()
        idsCollection = db.collection(FIREBASE_COLLECTION_NAME_IDS)
        Stetho.initializeWithDefaults(this)

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        component.inject(this)
        tokenTracker = object :VKTokenExpiredHandler{
            override fun onTokenExpired() {
                sPref.setTokenValidation(false)
            }
        }
        VK.addTokenExpiredHandler(tokenTracker)
    }
}
