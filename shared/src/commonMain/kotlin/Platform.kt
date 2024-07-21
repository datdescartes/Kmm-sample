import io.ktor.client.plugins.logging.Logger

interface Platform {
    val name: String
    val logger: Logger?
        get() = null
}

expect fun getPlatform(): Platform