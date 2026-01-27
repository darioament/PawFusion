package fina.dario.pawfusion.core

import androidx.compose.runtime.Stable
import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import org.jetbrains.compose.resources.StringResource

@Stable
data class BreedsState(
    val error: String? = null,
    val loading: Boolean = false,
    val breeds: List<UiBreedListItem> = emptyList()
)
