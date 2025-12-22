package fina.dario.pawfusion.di

import androidx.room.RoomDatabase
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.core.database.getFavoriteBreedDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module{
    //core
    single<HttpClientEngine>{ Android.create()}
    singleOf(::getFavoriteBreedDatabaseBuilder).bind<RoomDatabase.Builder<FavoriteBreedDatabase>>()
}