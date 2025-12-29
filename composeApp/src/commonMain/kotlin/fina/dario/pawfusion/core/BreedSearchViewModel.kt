package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BreedSearchViewModel: ViewModel()
{
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun setSearching()
    {
        _isSearching.update{
            it.not()
        }
    }
}


