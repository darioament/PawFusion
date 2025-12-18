package fina.dario.pawfusion.breed.presentation

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
data class BreedsState(
    val error: StringResource? = null,
    val loading: Boolean = false,
    val breeds: List<UiBreedListItem> = emptyList()
)
