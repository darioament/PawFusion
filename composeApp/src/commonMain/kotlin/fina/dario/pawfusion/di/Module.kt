package fina.dario.pawfusion.di

import androidx.room.RoomDatabase
import fina.dario.pawfusion.models.remote.impl.KtorBreedsRemoteDataSource
import fina.dario.pawfusion.models.domain.usecase.GetBreedDetailsUseCase
import fina.dario.pawfusion.models.domain.usecase.GetBreedsListUseCase
import fina.dario.pawfusion.models.domain.api.BreedsRemoteDataSource
import fina.dario.pawfusion.core.BreedFavoritesViewModel
import fina.dario.pawfusion.core.SearchEngineViewModel
import fina.dario.pawfusion.core.BreedsListViewModel
import fina.dario.pawfusion.core.database.favoriteBreed.FavoriteBreedDatabase
import fina.dario.pawfusion.core.database.favoriteBreed.getFavoriteBreedDatabase
import fina.dario.pawfusion.core.network.HttpClientFactory
import fina.dario.pawfusion.core.ThemeViewModel
import fina.dario.pawfusion.models.data.domain.usecase.DeleteFavoriteBReedByIdUseCase
import fina.dario.pawfusion.models.data.domain.usecase.DeleteFavoriteBReedByIdUseCaseImpl
import fina.dario.pawfusion.models.data.domain.usecase.GetAllFavoriteBreedsUseCase
import fina.dario.pawfusion.models.data.domain.usecase.GetAllFavoriteBreedsUseCaseImpl
import fina.dario.pawfusion.models.data.domain.usecase.insertFavoriteBreedUseCase
import fina.dario.pawfusion.models.data.domain.usecase.insertFavoriteBreedUseCaseImpl
import fina.dario.pawfusion.resources.LocalDataSource
import fina.dario.pawfusion.resources.LocalDataSourceImpl
import fina.dario.pawfusion.resources.Repository
import fina.dario.pawfusion.resources.RepositoryImpl
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
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
    singleOf(::BreedFavoritesViewModel)
    singleOf(::SearchEngineViewModel)
    singleOf(::ThemeViewModel)
    // Repo source
    singleOf(::LocalDataSourceImpl).bind<LocalDataSource>()
    singleOf(::RepositoryImpl).bind<Repository>()

    factory {
        GetAllFavoriteBreedsUseCaseImpl(get())
    }.bind<GetAllFavoriteBreedsUseCase>()

    factory {
        insertFavoriteBreedUseCaseImpl(get())
    }.bind<insertFavoriteBreedUseCase>()

    factory {
        DeleteFavoriteBReedByIdUseCaseImpl(get())
    }.bind<DeleteFavoriteBReedByIdUseCase>()


    // Api needed functions
    singleOf(::GetBreedsListUseCase)
    singleOf(::KtorBreedsRemoteDataSource).bind<BreedsRemoteDataSource>()
    singleOf(::GetBreedDetailsUseCase)
}