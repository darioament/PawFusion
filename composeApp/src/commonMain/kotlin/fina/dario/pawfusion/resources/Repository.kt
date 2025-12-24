package fina.dario.pawfusion.resources

import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal interface Repository{
    suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>>
    suspend fun insert(favoriteBreedEntity: FavoriteBreedEntity)
    suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedEntity?
    suspend fun deleteFavoriteBreedById(breedId: String)
}

internal class RepositoryImpl(
    //private val service: Service,
    private val localDataSource: LocalDataSource
): Repository{
    override suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>> =
        localDataSource.getAllFavoriteBreeds()

    override suspend fun insert(favoriteBreedEntity: FavoriteBreedEntity) {
        localDataSource.insert(favoriteBreedEntity)
    }

    override suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedEntity? =
        localDataSource.getFavoriteBreedById(breedId)


    override suspend fun deleteFavoriteBreedById(breedId: String) {
        localDataSource.deleteFavoriteBreedById(breedId)
    }
}