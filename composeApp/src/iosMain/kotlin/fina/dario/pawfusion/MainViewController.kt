package fina.dario.pawfusion

import androidx.compose.ui.window.ComposeUIViewController
import fina.dario.pawfusion.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }