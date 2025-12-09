package fina.dario.pawfusion

import android.app.Application
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
    }
}