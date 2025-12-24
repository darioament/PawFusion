package fina.dario.pawfusion.core.database.favoriteBreed

import androidx.room.Database
import androidx.room.RoomDatabase
import fina.dario.pawfusion.models.dao.FavoriteBreedDao
import fina.dario.pawfusion.models.dao.FavoriteBreedEntity

@Database( entities = [FavoriteBreedEntity::class], version = 2 )
abstract class FavoriteBreedDatabase : RoomDatabase()  {
     abstract fun favoriteBreedDao(): FavoriteBreedDao
}