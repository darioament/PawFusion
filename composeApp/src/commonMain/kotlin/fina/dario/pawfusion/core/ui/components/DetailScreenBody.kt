package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fina.dario.pawfusion.core.BreedFavoritesViewModel
import fina.dario.pawfusion.core.BreedsListViewModel
import fina.dario.pawfusion.core.ThemeViewModel
import org.koin.compose.koinInject


@Composable
fun DetailScreenBody(
    breed: UiBreedListItem,
    onNavigateBack: () -> Unit,
) {
    val breedListViewModel: BreedsListViewModel = koinInject()
    val breedsSearchViewModel: BreedFavoritesViewModel = koinInject()
    val themeViewModel: ThemeViewModel = koinInject()
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()
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
                    IconButton(onClick =
                        {
                            breedListViewModel.toggleFavorite()
                        }) {
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


            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
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
                            Spacer( modifier = Modifier.width(15.dp))
                            AttributeCard(
                                title = "Female Weight",
                                subtitle = breed?.female_weight?.max.toString()
                            )
                            Spacer( modifier = Modifier.width(15.dp))
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

