package fina.dario.pawfusion.models.data.mapper

import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import fina.dario.pawfusion.models.data.FavoriteBreedModel

fun FavoriteBreedEntity.toFavoriteBreedModel() = FavoriteBreedModel(
    id = id,
    type = type,
    description = description,
    averageLifeSpan = averageLifeSpan,
    hypoallergenic = hypoallergenic,
)

fun FavoriteBreedModel.toFavoriteBreedEntity() = FavoriteBreedEntity(
    id = id,
    type = type,
    description = description,
    averageLifeSpan = averageLifeSpan,
    hypoallergenic = hypoallergenic,
)