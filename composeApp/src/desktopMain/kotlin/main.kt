import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.dat.kmp.shared.data.DataStoreFactory
import me.dat.kmp.shared.di.Koin

fun main() {
    Koin.initKoin(DataStoreFactory())
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "datKMP",
        ) {
            App()
        }
    }
}