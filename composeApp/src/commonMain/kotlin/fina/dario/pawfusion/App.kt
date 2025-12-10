package fina.dario.pawfusion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fina.dario.pawfusion.breed.presentation.BreedState
import fina.dario.pawfusion.breed.presentation.BreedsListScreen
import fina.dario.pawfusion.breed.presentation.UiBreedListItem
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    TopAppBar()
    BreedsListScreen{ }
    /*val state = UiBreedListItem(
        id = "1",
        type = "German Shephard",
        description = "Brown and black stronk dog",
        averageLifeSpan = 10
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().height(400.dp),
        ){
            BreedListItem(
                state,
                {}
            )
        }
    }*/


}


@Composable
fun BreedsListComponent(
    state: BreedState,
    onBreedClicked: (String) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        BreedList(
            breeds = state.breeds,
            onBreedClicked = onBreedClicked
        )
    }
}

@Composable
fun BreedList(
    breeds: List<UiBreedListItem>,
    onBreedClicked: (String) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
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

@Composable
fun BreedListItem(
    breed: UiBreedListItem,
    onBreedClicked: (String) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding( 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                Text(
                    text = breed.type,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = breed.description,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Average lifespan: ${breed.averageLifeSpan}"
                )
            }

        }
    }
}
@Composable
fun Title(text: String, size: Int){
    Text(
        text = text,
        fontSize = size.sp,
        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                title = {
                    Text(
                        "PawFusion",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },

            )
        },
    ) {
    }
}