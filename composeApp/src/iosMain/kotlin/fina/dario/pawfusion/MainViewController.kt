package fina.dario.pawfusion

import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.ViewModel
import dev.icerock.moko.biometry.BiometryAuthenticator
import dev.icerock.moko.resources.desc.desc
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.di.initKoin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    class SampleViewModel(
        val biometryAuthenticator: BiometryAuthenticator
    ) : ViewModel() {

        fun tryToAuth() = CoroutineScope(Dispatchers.IO).launch {
            try {
                val isSuccess = biometryAuthenticator.checkBiometryAuthentication(
                    requestTitle = "Biometry".desc(),
                    requestReason = "Just for test".desc(),
                    failureButtonText = "Oops".desc(),
                    allowDeviceCredentials = false // true - if biometric permission is not granted user can authorise by device creds
                )

                if (isSuccess) {
                    // Do something onSuccess
                }
            } catch (throwable: Throwable) {
                // Do something onFailed
            }
        }
    }
    App()
}