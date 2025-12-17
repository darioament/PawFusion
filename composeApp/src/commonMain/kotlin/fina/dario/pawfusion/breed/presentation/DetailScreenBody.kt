package fina.dario.pawfusion.breed.presentation

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fina.dario.pawfusion.core.components.RoundendIcon
import fina.dario.pawfusion.core.theme.BreedRoutineTheme


@Composable
fun DetailScreenBody(
    breed: UiBreedListItem,
    onNavigateBack: () -> Unit,
) {
    BreedRoutineTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                modifier = Modifier.padding(vertical = 65.dp, horizontal = 20.dp)
            ){
                IconButton(onClick = onNavigateBack ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                        IconButton(onClick = { }  ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                                tint = Color.Gray
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
                        text = breed.type,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        color = Color.Black
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
                                    title = "Breed",
                                    subtitle = breed.type
                                )
                                Spacer( modifier = Modifier.width(30.dp))
                                AttributeCard(
                                    title = "Life span",
                                    subtitle = breed.averageLifeSpan.toString()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = breed.description,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = Color.Gray,
                            textAlign = TextAlign.Start
                        )
                    }
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
    Card(
        modifier = Modifier
            .height(75.dp)
            .width(125.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.White, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = title,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = subtitle,
                    fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                    color = Color.Gray,
                )
            }

        }
    }

}

