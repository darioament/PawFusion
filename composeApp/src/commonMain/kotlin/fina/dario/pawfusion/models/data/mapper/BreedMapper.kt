package fina.dario.pawfusion.models.data.mapper

import fina.dario.pawfusion.core.ui.components.UiBreedListItem
import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import fina.dario.pawfusion.models.data.FavoriteBreedModel
import fina.dario.pawfusion.models.remote.dto.BreedItemDto
import fina.dario.pawfusion.models.remote.dto.BreedLifeDto
import fina.dario.pawfusion.models.remote.dto.BreedWeightDto
import fina.dario.pawfusion.models.domain.Breed.Breed
import fina.dario.pawfusion.models.domain.Breed.BreedAttributes
import fina.dario.pawfusion.models.domain.Breed.BreedLife
import fina.dario.pawfusion.models.domain.Breed.BreedWeight
import fina.dario.pawfusion.models.domain.model.BreedModel


fun BreedItemDto.toBreedModel() = BreedModel(
    breed = Breed(
        id = id,
        type = type,
        attributes = BreedAttributes(
            name = attributes.name,
            description = attributes.description,
            hypoallergenic = attributes.hypoallergenic,
            life = attributes.life.toBreedLife(),
            male_weight = attributes.male_weight.toBreedWeight(),
            female_weight = attributes.female_weight.toBreedWeight()
        ),
    )
)

fun BreedLifeDto.toBreedLife() = BreedLife(
    min = min,
    max = max,
)
fun BreedWeightDto.toBreedWeight() = BreedWeight(
    min = min,
    max = max,
)

fun UiBreedListItem.toFavoriteBreedModel() = FavoriteBreedModel(
    id = id,
    type = type,
    description = description,
    averageLifeSpan = averageLifeSpan,
    hypoallergenic = hypoallergenic
)
