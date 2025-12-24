package fina.dario.pawfusion.resources

import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.models.dao.FavoriteBreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal interface LocalDataSource{
    suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>>
}

internal class LocalDataSourceImpl(
    private val roomDatabase: FavoriteBreedDatabase,
): LocalDataSource{
    override suspend fun getAllFavoriteBreeds(): Flow<List<FavoriteBreedEntity>> =
        roomDatabase.favoriteBreedDao().getAllFavoriteBreed()
}