package ua.aniloom.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ua.aniloom.di.DiModules

class AniLoomApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AniLoomApp)
            modules(DiModules().appModules)
        }
    }
}