package fina.dario.pawfusion.core.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cafe.adriel.voyager.transitions.CrossfadeTransition
import fina.dario.pawfusion.core.BreedFavoritesViewModel
import fina.dario.pawfusion.core.BreedsListViewModel
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.core.ui.navigation.voyager.NavHost
import fina.dario.pawfusion.core.ui.theme.BreedRoutineTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenBody(
    breed: UiBreedListItem,
    onNavigateBack: () -> Unit,
) {
    val breedListViewModel: BreedsListViewModel = koinInject()
    val themeViewModel: ThemeViewModel = koinInject()
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()
    var showErrorDialog by remember { mutableStateOf(false) }
    val state by breedListViewModel.state.collectAsState()
    val errorWasShown by breedListViewModel.errorWasShown.collectAsState()
    var onClick by remember { mutableStateOf({}) }
    var showPopup by remember { mutableStateOf(false) }

    if(state.error != null){
        showErrorDialog = true
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.padding(vertical = 65.dp, horizontal = 20.dp)
        ){
            IconButton(onClick = onNavigateBack ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "Localized description",
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .background(color = MaterialTheme.colorScheme.outlineVariant, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.outlineVariant, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if(errorWasShown){
                        onClick = {}
                    }
                    else{
                        onClick = { breedListViewModel.toggleFavorite(); showPopup = true }
                    }
                    IconButton(onClick = onClick) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                            tint =  if(breed.isFavorite) Color.Gray else Color.White
                        )
                    }
                }
            }

        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = breed?.type ?: "Empty",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = if(isDarkTheme) Color.Gray else Color.Black
                )
            }
            Spacer(modifier = Modifier.height(30.dp ))
            //PartialBottomSheet(breed)
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(25.dp)
                    ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            AttributeCard(
                                title = "Male Weight",
                                subtitle = breed?.male_weight?.max.toString()
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            AttributeCard(
                                title = "Female Weight",
                                subtitle = breed?.female_weight?.max.toString()
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            AttributeCard(
                                title = "Life Span",
                                subtitle = breed?.averageLifeSpan.toString()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = breed?.description ?: "Nothing",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = Color.Gray,
                        textAlign = TextAlign.Start
                    )
                }
            }

        }
    }
    if(showErrorDialog){
        ErrorDialog(Modifier, state.error.toString(), onDismiss = {showErrorDialog = false; breedListViewModel.clearError()} )
    }

    if (showPopup) {
        AutoDismissPopup(
            breedType = breed.type,
            isAddToFavorite = breedListViewModel.isInFavorites(breed.id),
            onDismiss = { showPopup = false },
            durationMillis = 2000 // 3 seconds
        )
    }
}
@Composable
fun AutoDismissPopup(
    breedType: String,
    isAddToFavorite: Boolean,
    onDismiss: () -> Unit,
    durationMillis: Long
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(durationMillis)
        visible = false
        delay(300)
        onDismiss()
    }

    Popup(
        alignment = Alignment.TopCenter,
        offset = IntOffset(0, 100),
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f),
            exit = fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF333333), RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                if(isAddToFavorite)
                    Text(
                        text = "$breedType added to favorites!",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = Color.White)
                else
                    Text(
                        text ="$breedType removed from favorites!",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = Color.White)

            }
        }
    }
}





@Composable
fun AttributeCard(
    title: String,
    subtitle: String,
){
    val isDarkTheme = isSystemInDarkTheme()
    Card(
        modifier = Modifier
            .height(70.dp)
            .width(110.dp)
            .background(color = if(isDarkTheme) Color.Gray else Color.White, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize().background(color = if(isDarkTheme) Color.Gray else Color.White, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = title,
                    color = if(isDarkTheme) Color.DarkGray else Color.Gray,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = subtitle,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    color = if(isDarkTheme) Color.DarkGray else Color.Gray,
                )
            }

        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet(
    breed: UiBreedListItem?
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(25.dp)
                    ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            AttributeCard(
                                title = "Male Weight",
                                subtitle = breed?.male_weight?.max.toString()
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            AttributeCard(
                                title = "Female Weight",
                                subtitle = breed?.female_weight?.max.toString()
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            AttributeCard(
                                title = "Life Span",
                                subtitle = breed?.averageLifeSpan.toString()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = breed?.description ?: "Nothing",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = Color.Gray,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

