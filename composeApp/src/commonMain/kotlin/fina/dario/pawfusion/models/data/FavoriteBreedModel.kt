package fina.dario.pawfusion.models.data

import androidx.room.PrimaryKey

data class FavoriteBreedModel(
    val id: String,
    val type: String,
    val description: String,
    val averageLifeSpan: Int,
    val hypoallergenic: Boolean,
)
