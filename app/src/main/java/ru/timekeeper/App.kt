package ru.timekeeper

import android.app.Application
import ru.timekeeper.di.component.AppComponent
import ru.timekeeper.di.component.DaggerAppComponent
import ru.timekeeper.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        //component.inject(this)
    }
}