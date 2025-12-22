package fina.dario.pawfusion.favorites.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteBreedDao {
    @Upsert
    suspend fun insert(favoriteBreedEntity: FavoriteBreedEntity)

    @Query("SELECT * FROM FavoriteBreedEntity")
    fun getAllFavoriteBreed(): Flow<List<FavoriteBreedEntity>>

    @Query("SELECT * FROM FavoriteBreedEntity WHERE id = :breedId")
    suspend fun getFavoriteBreedById(breedId: String): FavoriteBreedEntity?

    @Query("DELETE FROM FavoriteBreedEntity WHERE id = :breedId")
    suspend fun deleteFavoriteBreedById(breedId: String)

}