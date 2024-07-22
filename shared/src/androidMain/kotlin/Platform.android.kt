import android.os.Build
import android.util.Log
import io.ktor.client.plugins.logging.Logger
import me.dat.kmp.shared.data.DataStoreFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val logger: Logger = object : Logger {
        override fun log(message: String) {
            Log.v("Logger Ktor =>", message)
        }
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()

val platformModule = module {
    single<DataStoreFactory> { DataStoreFactory(androidApplication()) }
}