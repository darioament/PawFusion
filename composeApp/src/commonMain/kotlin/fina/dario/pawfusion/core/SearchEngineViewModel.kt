package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchEngineViewModel: ViewModel()
{
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()
    private val log = Logger.withTag("PawFusionLogger")

    fun setSearching()
    {
        _isSearching.update{
            it.not()
        }
    }
    fun onSearchTextChange(text: String) {
        _searchText.update{ text }
        log.i("Search text changed to: ${_searchText.value}")
        log.i("Search text(state flow) changed to: ${searchText.value}")
    }
    fun clearSearch(){
        _searchText.update{ "" }
    }

}


