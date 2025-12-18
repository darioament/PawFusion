package fina.dario.pawfusion.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fina.dario.pawfusion.breed.presentation.UiBreedListItem

@Composable
fun BreedListItem(
    breed: UiBreedListItem,
    onBreedClicked: (String) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize().padding(vertical = 7.dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .height(100.dp).width(375.dp)
                .background(color = Color.White)
                .padding(2.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            onClick = {onBreedClicked(breed.id)}
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RoundendIcon(breed.type )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column(){
                        Text(
                            text = breed.type,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Average lifespan:${breed.averageLifeSpan}",
                            )
                    }
                }

            }
        }
    }
}


