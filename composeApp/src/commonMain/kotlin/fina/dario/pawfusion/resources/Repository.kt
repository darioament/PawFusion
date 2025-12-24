package fina.dario.pawfusion.resources

import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal interface Repository{
    suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>>
}

internal class RepositoryImpl(
    //private val service: Service,
    private val localDataSource: LocalDataSource
): Repository{
    override suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>> =
        localDataSource.getAllFavoriteBreeds()
}