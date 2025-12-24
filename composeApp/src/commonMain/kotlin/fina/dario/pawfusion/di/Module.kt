package fina.dario.pawfusion.di

import androidx.room.RoomDatabase
import fina.dario.pawfusion.breed.data.remote.impl.KtorBreedsRemoteDataSource
import fina.dario.pawfusion.breed.domain.GetBreedDetailsUseCase
import fina.dario.pawfusion.breed.domain.GetBreedsListUseCase
import fina.dario.pawfusion.breed.domain.api.BreedsRemoteDataSource
import fina.dario.pawfusion.core.components.BreedFavoritesViewModel
import fina.dario.pawfusion.core.components.BreedsListViewModel
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.core.database.favoriteBreed.getFavoriteBreedDatabase
import fina.dario.pawfusion.core.network.HttpClientFactory
import fina.dario.pawfusion.resources.LocalDataSource
import fina.dario.pawfusion.resources.LocalDataSourceImpl
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration?= null) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule,

        )
    }


expect val platformModule: Module

val sharedModule = module {
    //core
    single<HttpClient> { HttpClientFactory.create(get()) }

    //Room database: Favorite
    single{
        getFavoriteBreedDatabase(get<RoomDatabase.Builder<FavoriteBreedDatabase>>())
    }
    //breedView model
    singleOf(::BreedsListViewModel)
    // localDataSource
    singleOf(::LocalDataSourceImpl).bind<LocalDataSource>()

    viewModel { BreedFavoritesViewModel() }
    singleOf(::GetBreedsListUseCase)
    singleOf(::KtorBreedsRemoteDataSource).bind<BreedsRemoteDataSource>()
    singleOf(::GetBreedDetailsUseCase)
}