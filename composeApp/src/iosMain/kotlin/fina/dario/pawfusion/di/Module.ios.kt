package fina.dario.pawfusion.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module


actual val platformModule = module {

    single<HttpClientEngine>{ Darwin.create()}
}