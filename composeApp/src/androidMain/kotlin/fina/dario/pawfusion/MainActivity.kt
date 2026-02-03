package fina.dario.pawfusion

import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mmk.kmpnotifier.permission.permissionUtil
import fina.dario.pawfusion.biometrics.BiometricPromptManager

//import chaintech.network.connectivitymonitor.ConnectivityMonitor

class MainActivity : AppCompatActivity() {
    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //ConnectivityMonitor.initialize(this)
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val permissionUtil by permissionUtil()
        permissionUtil.askNotificationPermission() //this will ask permission in Android 13(API Level 33) or above, otherwise permission will be granted.
        setContent {
            App()
        }
    }
}

