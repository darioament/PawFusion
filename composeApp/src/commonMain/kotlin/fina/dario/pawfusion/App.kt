package fina.dario.pawfusion

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.mmk.kmpnotifier.notification.NotificationImage
import com.mmk.kmpnotifier.notification.Notifier
import com.mmk.kmpnotifier.notification.NotifierManager
//import fina.dario.pawfusion.core.ConnectivityViewModel
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.core.ui.navigation.voyager.NavHost
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.favorites.FavoritesTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.home.HomeTab
import fina.dario.pawfusion.core.ui.navigation.voyager.tab.settings.SettingsTab
import fina.dario.pawfusion.core.ui.theme.BreedRoutineTheme
import kotlinx.coroutines.flow.map
import org.koin.compose.koinInject
import kotlin.random.Random


@Composable
fun App() {
//    val connectionViewModel: ConnectivityViewModel = koinInject()
//    val connectivityStatus by connectionViewModel.connectivityStatus.collectAsState()
    val themeViewModel: ThemeViewModel = koinInject()
    val prefs: DataStore<Preferences> = koinInject()
    val theme by prefs
        .data.map { dataStore ->
            val themeKey = booleanPreferencesKey("theme")
            dataStore[themeKey]  ?: false
        }.collectAsState(false  )
    LaunchedEffect(theme){
        themeViewModel.setTheme(theme)
    }
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()

    val notifier = NotifierManager.getLocalNotifier()
    notifier.notify {
        id= Random.nextInt(0, Int.MAX_VALUE)
        title = "PawFusion"
        body = "Welcome back to PawFusion!"
        payloadData = mapOf(
            Notifier.KEY_URL to "https://github.com/mirzemehdi/KMPNotifier/",
            "extraKey" to "randomValue"
        )
        image = NotificationImage.Url("https://th.bing.com/th/id/R.41aa651d54d0a80c96af05c0e46d1c65?rik=PtnZ2F%2bGwJUEfA&pid=ImgRaw&r=0")
    }

    Crossfade(targetState = isDarkTheme, animationSpec = tween()){ newTheme ->
        BreedRoutineTheme(isDarkTheme) {
            NavHost()
        }
    }

}



