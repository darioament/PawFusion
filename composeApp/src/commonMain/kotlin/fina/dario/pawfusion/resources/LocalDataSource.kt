package fina.dario.pawfusion.resources

import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal interface LocalDataSource{
    suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>>
    suspend fun insert(favoriteBreedEntity: FavoriteBreedEntity)
    suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedEntity?
    suspend fun deleteFavoriteBreedById(breedId: String)
}

internal class LocalDataSourceImpl(
    private val roomDatabase: FavoriteBreedDatabase,
): LocalDataSource{
    override suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>> =
        roomDatabase.favoriteBreedDao().getAllFavoriteBreed()

    override suspend fun insert(favoriteBreedEntity: FavoriteBreedEntity) {
        roomDatabase.favoriteBreedDao().insert(favoriteBreedEntity)
    }

    override suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedEntity? =
        roomDatabase.favoriteBreedDao().getFavoriteBreedById(breedId)


    override suspend fun deleteFavoriteBreedById(breedId: String) {
        roomDatabase.favoriteBreedDao().deleteFavoriteBreedById(breedId)
    }
}