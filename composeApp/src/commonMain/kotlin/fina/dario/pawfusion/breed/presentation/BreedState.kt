package fina.dario.pawfusion.breed.presentation

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
data class BreedState(
    val error: StringResource? = null,
    val breeds: List<UiBreedListItem> = emptyList()
)