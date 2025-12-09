package fina.dario.pawfusion.breed.domain.model

import fina.dario.pawfusion.breed.data.remote.dto.BreedAttributesDto
import fina.dario.pawfusion.breed.data.remote.dto.BreedLifeDto
import fina.dario.pawfusion.breed.domain.Breed.Breed
import fina.dario.pawfusion.breed.domain.Breed.BreedAttributes
import fina.dario.pawfusion.breed.domain.Breed.BreedLife

data class BreedModel(
    val breed: Breed,
)