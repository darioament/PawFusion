package fina.dario.pawfusion.breed.data.mapper

import fina.dario.pawfusion.breed.data.remote.dto.BreedItemDto
import fina.dario.pawfusion.breed.data.remote.dto.BreedLifeDto
import fina.dario.pawfusion.breed.data.remote.dto.BreedWeightDto
import fina.dario.pawfusion.breed.domain.Breed.Breed
import fina.dario.pawfusion.breed.domain.Breed.BreedAttributes
import fina.dario.pawfusion.breed.domain.Breed.BreedLife
import fina.dario.pawfusion.breed.domain.Breed.BreedWeight
import fina.dario.pawfusion.breed.domain.model.BreedModel


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
