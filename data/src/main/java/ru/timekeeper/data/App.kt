package ru.timekeeper.data

import android.app.Application
import ru.timekeeper.data.di.component.AppComponent
import ru.timekeeper.data.di.component.DaggerAppComponent
import ru.timekeeper.data.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var component : AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        //component.inject(this)
    }
}