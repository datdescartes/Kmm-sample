package me.dat.kmp.shared.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import getPlatform
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import me.dat.kmp.shared.data.DataStoreFactory
import me.dat.kmp.shared.data.SettingsRepository
import me.dat.kmp.shared.data.SpaceXRepository
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.network.SpaceXApi
import me.dat.kmp.shared.viewmodel.RocketLaunchViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

object Koin {
    fun initKoin(factory: DataStoreFactory, appDeclaration: KoinAppDeclaration = {}) =
        startKoin {
            appDeclaration()
            modules(
                networkModule,
                dataStoreModule(factory),
                commonModule,
            )
        }

    private val commonModule = module {
        single { SettingsRepository() }
        single { SpaceXRepository() }
        single { RocketLaunchViewModel() }
    }

    private val networkModule = module {
        single { createJson() }
        single { createHttpClient(get()) }
        single { SpaceXApi(get()) }
    }

    private fun createJson(): Json = Json {
        ignoreUnknownKeys = true
        useAlternativeNames = false
    }

    private fun createHttpClient(json: Json): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(json)
            install(Logging) {
                logger = getPlatform().logger ?: Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

    private fun dataStoreModule(factory: DataStoreFactory) = module {
        single<AppDatabase> {
            factory.createRoomDatabase()
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
        single<DataStore<Preferences>> {
            factory.createPrefDataStore()
        }
    }
}