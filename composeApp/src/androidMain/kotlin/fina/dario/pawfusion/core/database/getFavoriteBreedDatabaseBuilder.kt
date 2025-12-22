package fina.dario.pawfusion.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase

fun getFavoriteBreedDatabaseBuilder(context: Context): RoomDatabase.Builder<FavoriteBreedDatabase>{
    val dbFile = context.getDatabasePath("favoriteBreed.db")

    return Room.databaseBuilder<FavoriteBreedDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}