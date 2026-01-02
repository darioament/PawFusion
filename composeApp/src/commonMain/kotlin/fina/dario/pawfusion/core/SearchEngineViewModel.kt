package fina.dario.pawfusion.core

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

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
    @OptIn(ExperimentalTime::class)
    fun onSearchTextChange(text: String) {
        _searchText.update{ text }
        log.i("Current time of update is: ${Clock.System.now()}")
    }
    fun clearSearch(){
        _searchText.update{ "" }
    }

}


