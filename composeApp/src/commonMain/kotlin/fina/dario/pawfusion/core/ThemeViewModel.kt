package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ThemeViewModel: ViewModel() {
    private val _isDarkTheme = MutableStateFlow(false)
    val useDynamicColors = _isDarkTheme.asStateFlow()
    private val log = Logger.withTag("PawFusionLogger")


    fun setDarkTheme(){
        _isDarkTheme.update{
            true
        }
        log.i("Dark theme set, current value of isDark: ${_isDarkTheme.value}")
    }

    fun setLightTheme(){
        _isDarkTheme.update{
            false
        }
        log.i("Light theme set, current value of isDark: ${_isDarkTheme.value}")
    }
}