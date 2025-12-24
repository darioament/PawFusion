package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesBreedCard(
    type: String,
    averageLifeSpan: Int,
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
                    RoundendIcon(type )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column(){
                        Text(
                            text = type,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Average lifespan:${averageLifeSpan}",
                        )
                    }
                }

            }
        }
    }
}