import android.os.Build
import android.util.Log
import io.ktor.client.plugins.logging.Logger

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val logger: Logger = object : Logger {
        override fun log(message: String) {
            Log.v("Logger Ktor =>", message)
        }
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()