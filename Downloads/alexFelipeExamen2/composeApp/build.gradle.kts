import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "2.1.0"
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
            implementation("org.slf4j:slf4j-nop:1.7.36")
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
        desktopMain.dependencies {
            implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}


compose.desktop {
    application {
        mainClass = "ipar.alexfelipe.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ipar.alexfelipe"
            packageVersion = "1.0.0"
        }
    }
}
sqldelight {
    databases {
        create("Database") {
            packageName.set("ipar.alexfelipe.data")
            dialect("app.cash.sqldelight:sqlite-3-38-dialect:2.0.2")
        }
    }
}
