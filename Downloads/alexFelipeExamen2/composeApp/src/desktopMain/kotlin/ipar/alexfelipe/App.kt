package ipar.alexfelipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import alexfelipeexamen2.composeapp.generated.resources.Res
import alexfelipeexamen2.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.cash.sqldelight.db.SqlDriver
import ipar.alexfelipe.data.Database
import kotlinx.serialization.Serializable

@Serializable
object DatabaseConfig {
    val name: String = "teatre.db"
    val development: Boolean = true
}

@Composable
@Preview
fun App(sqlDriver: SqlDriver) {
    val database = Database(sqlDriver)
    Database.Schema.create(sqlDriver)
    if (DatabaseConfig.development) {
        insertDevelopmentData(database)
    }
    MaterialTheme {
        val controller = rememberNavController()
        NavHost(controller, startDestination = HomeRoute) {
            composable<HomeRoute> { HomeScreen(controller) }
            composable<PreusRoute> { PreusRoute(controller) }
            composable<> {  }
        }
    }
}
@Serializable
object
@Serializable
object PreusRoute

@Composable
fun PreusRoute(controller: NavController){

    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize() ){
        Nav(controller)

    }
}

@Serializable
object HomeRoute

@Composable
fun HomeScreen(controller: NavController){
    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
        Nav(controller)
        Text("Configurar Preus")
        Button(onClick = { controller.navigate(PreusRoute) }) {
            Text("Preus")
        }
    }
}

fun insertDevelopmentData(db: Database) {
    db.espectacleQueries.insert("Les mariposes","obra de teatre alegre")
    db.espectacleQueries.insert("Dolores","obra de teatre trist")
    db.sessioQueries.insert("Dilluns",1)
    db.sessioQueries.insert("Dimarts",2)
    db.zonaQueries.insert("estandar")
    db.zonaQueries.insert("premium")
    db.preuQueries.insertSensePreu("Dilluns",1)
    db.preuQueries.insertSensePreu("Dilluns",2)
    db.preuQueries.insertSensePreu("Dimarts",1)
    db.preuQueries.insertSensePreu("Dimarts",1)
    db.butacaQueries.insert(1)
    db.butacaQueries.insert(1)
    db.butacaQueries.insert(1)
    db.butacaQueries.insert(2)
    db.butacaQueries.insert(2)
    db.butacaQueries.insert(2)
    db.entradaQueries.insert("Dilluns",1)
    db.entradaQueries.insert("Dilluns",2)
    db.entradaQueries.insert("Dimarts",4)
    db.entradaQueries.insert("Dimarts",5)
}
@Composable
fun Nav(controller: NavController) {
    TopAppBar(
        title = { Text("Gran teatre")},
        actions = {
            IconButton(onClick = { controller.navigate(HomeRoute) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
        }
    )
}