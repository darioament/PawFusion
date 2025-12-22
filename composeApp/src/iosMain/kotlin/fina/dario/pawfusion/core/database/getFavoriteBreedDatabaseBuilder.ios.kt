package fina.dario.pawfusion.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import platform.Foundation.NSHomeDirectory

fun getFavoriteBreedDatabaseBuilder(): RoomDatabase.Builder<FavoriteBreedDatabase>{
    val dbFile = NSHomeDirectory() + "/portfolio.db"
    return Room.databaseBuilder<FavoriteBreedDatabase>(
        name = dbFile
    )
}