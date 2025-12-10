package fina.dario.pawfusion.di


import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module


actual val platformModule = module {

    single<HttpClientEngine>{ Darwin.create()}
}