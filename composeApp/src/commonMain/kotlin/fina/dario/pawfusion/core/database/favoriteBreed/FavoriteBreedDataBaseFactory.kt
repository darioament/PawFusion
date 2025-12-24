package fina.dario.pawfusion.core.database.favoriteBreed

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.http.Headers.Companion.build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

/*
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object FavoriteBreedDataBaseCreator: RoomDatabaseConstructor<FavoriteBreedDatabase>
*/

fun getFavoriteBreedDatabase(
    builder: RoomDatabase.Builder<FavoriteBreedDatabase>
): FavoriteBreedDatabase {
    return builder
        //.addMigrations(MIGRATIONS)
        //.fallbackToDestructiveMigrationOnDowngrade()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}