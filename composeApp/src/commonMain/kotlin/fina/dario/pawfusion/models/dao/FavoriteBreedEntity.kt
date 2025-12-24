package fina.dario.pawfusion.models.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteBreedEntity(
    @PrimaryKey val id: String,
    val type: String,
    val description: String,
    val averageLifeSpan: Int,
    val hypoallergenic: Boolean,
    //@ForeignKey val male_weight: BreedWeight,
    //@ForeignKey  val female_weight: BreedWeight,
)

@Entity
data class BreedWeight(
    @PrimaryKey val id: String,
    val min: Int,
    val max: Int,
)