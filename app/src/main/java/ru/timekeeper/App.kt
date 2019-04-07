package ru.timekeeper

import android.app.AppComponentFactory
import android.app.Application
import ru.timekeeper.di.AppComponent
import ru.timekeeper.di.AppModule
import ru.timekeeper.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var component : AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        component.inject(this)
    }
}