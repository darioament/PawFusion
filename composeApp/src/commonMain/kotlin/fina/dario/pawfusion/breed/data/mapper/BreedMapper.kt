package fina.dario.pawfusion.breed.data.mapper

import fina.dario.pawfusion.breed.data.remote.dto.BreedItemDto
import fina.dario.pawfusion.breed.domain.Breed.Breed
import fina.dario.pawfusion.breed.domain.Breed.BreedAttributes
import fina.dario.pawfusion.breed.domain.Breed.BreedLife
import fina.dario.pawfusion.breed.domain.model.BreedModel
import kotlin.String

fun BreedItemDto.toBreedModel() = BreedModel(
    breed = Breed(
        id = id,
        type = type,
        attributes = BreedAttributes(
            name = attributes.name,
            description = attributes.description
        ),
        life = BreedLife(
            max = life.max,
            min = life.min
        ),
    )
)