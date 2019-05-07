package ru.timekeeper.common

import android.app.Application
import com.facebook.stetho.Stetho
import ru.timekeeper.BuildConfig
import ru.timekeeper.vkontakte.di.component.AppComponent
import ru.timekeeper.vkontakte.di.component.DaggerAppComponent
import ru.timekeeper.vkontakte.di.module.AppModule

class App : Application() {

    companion object {
        @Suppress("LateinitUsage")
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        //component.inject(this)
    }
}
