package me.dat.kmp

import android.app.Application
import me.dat.kmp.shared.data.DataStoreFactory
import me.dat.kmp.shared.di.Koin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class AndroidApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        Koin.initKoin(DataStoreFactory(this)) {
            androidContext(this@AndroidApp)
        }
    }
}