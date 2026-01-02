package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import fina.dario.pawfusion.models.domain.Breed.Breed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BreedSearchViewModel: ViewModel()
{
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun setSearching()
    {
        _isSearching.update{
            it.not()
        }
    }
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }


}


