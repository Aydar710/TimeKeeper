package ru.timekeeper

import android.app.Application
import com.facebook.stetho.Stetho
import ru.timekeeper.di.component.AppComponent
import ru.timekeeper.di.component.DaggerAppComponent
import ru.timekeeper.di.module.AppModule

class App : Application() {

    companion object {
        @Suppress("LateinitUsage")
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        //component.inject(this)
    }
}
