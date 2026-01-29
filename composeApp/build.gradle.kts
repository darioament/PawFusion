import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
    id("com.google.gms.google-services") version "4.4.0" apply false

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    


    
    sourceSets {
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:3.0.0")
            implementation("io.ktor:ktor-client-core:3.0.0")
        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.android)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)


            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlin.date.time)
            implementation(libs.androidx.lifecycle.viewmodel)

            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)
           // implementation(libs.androidx.sqlite)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor)


            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.transitions)

            // Network checker
            //implementation("network.chaintech:compose-connectivity-monitor:1.0.4")



            api(libs.kermit)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.navigation)

            // Push Notification library
            api("io.github.mirzemehdi:kmpnotifier:1.6.0")


            api("androidx.datastore:datastore:1.2.0")
            api("androidx.datastore:datastore-preferences:1.2.0")

            implementation(project(path = ":navigation"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            export("io.github.mirzemehdi:kmpnotifier:1.6.0")
            baseName = "ComposeApp"
            isStatic = true
        }
    }
}

android {
    namespace = "fina.dario.pawfusion"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "fina.dario.pawfusion"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
compose.resources {
    publicResClass = true
    packageOfResClass = "fina.dario.pawfusion.generated.resources"
}



room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    ksp(libs.room.compiler)
    debugImplementation(compose.uiTooling)
}

