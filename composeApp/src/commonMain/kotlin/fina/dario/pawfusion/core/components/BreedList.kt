package fina.dario.pawfusion.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BreedList(
    breeds: List<UiBreedListItem>,
    onBreedClicked: (String) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().wrapContentHeight()
    ){
        LazyColumn (
            modifier = Modifier.fillMaxSize().padding( horizontal = 5.dp),
        ) {
            items(breeds){ breed ->
                BreedListItem(
                    breed = breed,
                    onBreedClicked
                )
            }
        }
    }
}