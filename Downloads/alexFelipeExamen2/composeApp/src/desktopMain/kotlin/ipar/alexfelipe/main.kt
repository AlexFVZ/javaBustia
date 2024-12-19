package ipar.alexfelipe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.io.path.Path
import kotlin.io.path.deleteIfExists
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

fun main() = application {

    if (DatabaseConfig.development) {
        Path(DatabaseConfig.name).deleteIfExists()
    }

    val driver = JdbcSqliteDriver("jdbc:sqlite:${DatabaseConfig.name}")

    Window(
        onCloseRequest = ::exitApplication,
        title = "sqldelight",
    ) {
        App(driver)
    }
}