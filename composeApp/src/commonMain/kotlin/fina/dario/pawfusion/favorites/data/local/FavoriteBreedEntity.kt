package fina.dario.pawfusion.favorites.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import fina.dario.pawfusion.breed.presentation.UiBreedWeight


@Entity
data class FavoriteBreedEntity(
    @PrimaryKey val id: String,
    val type: String,
    val description: String,
    val averageLifeSpan: Int,
    val hypoallergenic: Boolean,
    val male_weight: UiBreedWeight,
    val female_weight: UiBreedWeight,
)