package fina.dario.pawfusion.core.ui.navigation.voyager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.mmk.kmpnotifier.notification.NotificationImage
import com.mmk.kmpnotifier.notification.Notifier
import com.mmk.kmpnotifier.notification.NotifierManager
import fina.dario.pawfusion.core.BreedsListViewModel
import fina.dario.pawfusion.core.ui.components.BreedsListScreen
import fina.dario.pawfusion.core.ui.components.ErrorDialog
import fina.dario.pawfusion.core.ui.components.LoadingScreen
import fina.dario.pawfusion.core.ui.components.TopAppBar
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import kotlin.random.Random


class HomeScreen: Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val breedListViewModel:BreedsListViewModel  = koinInject()
        val state by breedListViewModel.state.collectAsState()
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
        var showErrorDialog by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val errorWasShown by breedListViewModel.errorWasShown.collectAsState()

        if(state.loading){
            LoadingScreen()
        }
        else if(state.error != null){
            showErrorDialog = true
        }
        else{
            if( errorWasShown && state.error == null ){
                scope.launch {
                    breedListViewModel.getAllBreeds()
                }.invokeOnCompletion {
                    navigator?.push(HomeScreen())
                }
            }
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(navigator, scrollBehavior)
                },


                ) { innerPadding ->
                Spacer(modifier = Modifier.height(15.dp))
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    BreedsListScreen(navigator, onBreedClicked = {itemId->
                        navigator?.push(DetailScreen(id = itemId))
                    })
                }
            }
        }

        if(showErrorDialog){
            ErrorDialog(Modifier, state.error.toString(), onDismiss = {showErrorDialog = false; breedListViewModel.clearError()} )

        }



    }
}