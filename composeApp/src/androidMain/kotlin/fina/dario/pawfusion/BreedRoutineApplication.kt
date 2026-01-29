package fina.dario.pawfusion

import android.app.Application
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import fina.dario.pawfusion.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class BreedRoutineApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        initKoin{
             androidLogger()
            androidContext(this@BreedRoutineApplication)
        }
        NotifierManager.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = R.drawable.ic_launcher_foreground,
                showPushNotification = false,
            )
        )
    }
}