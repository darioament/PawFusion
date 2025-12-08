package fina.dario.pawfusion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform