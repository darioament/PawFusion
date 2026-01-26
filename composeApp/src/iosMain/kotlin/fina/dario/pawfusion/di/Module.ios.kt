package fina.dario.pawfusion.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import fina.dario.pawfusion.core.database.createDataStore
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.core.database.getFavoriteBreedDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


actual val platformModule = module {

    single<HttpClientEngine>{ Darwin.create()}
    singleOf(::getFavoriteBreedDatabaseBuilder).bind<RoomDatabase.Builder<FavoriteBreedDatabase>>()
    singleOf(::createDataStore).bind<DataStore<Preferences>>()

}