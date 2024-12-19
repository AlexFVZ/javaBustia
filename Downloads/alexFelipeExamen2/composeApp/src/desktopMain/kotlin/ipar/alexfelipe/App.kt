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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import app.cash.sqldelight.db.SqlDriver
import ipar.alexfelipe.data.Database
import ipar.alexfelipe.data.Espectacle
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
            composable<HomeRoute> { HomeScreen(controller,database) }
            composable<PreusRoute> { PreusScreen(controller,database) }
            composable<EspectacleRoute> { EspectacleScreen(controller,database) }
            composable<SessioRoutePreu> { entry -> SessioScreenPreu(database,controller, entry.toRoute()) }

        }
    }
}
@Serializable
data class SessioRoutePreu(val codi:Int)

@Composable
fun SessioScreenPreu(database: Database,controller: NavController,route:SessioRoutePreu){

    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
        Nav(controller)

    }
}
@Serializable
object EspectacleRoute

@Composable
fun EspectacleScreen(controller: NavController,database: Database){
    val espectacles = database.espectacleQueries.select().executeAsList()
    val espectacle= remember { mutableListOf(Espectacle(0,"","")) }
    val result= remember { mutableStateOf("") }
    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
        Nav(controller)
        Text("A qui espectacle vols posarli preu?")
        LazyColumn {
            items(espectacles) {espectacle -> especView(espectacle)}
        }
        TextField(
            value = espectacle.value.codi,
            placeholder = { Text("codi") },
            onValueChange = {espectacle.value = espectacle.value.copy(codi=it)}
        )
        Button(onClick = { controller.navigate(SessioRoutePreu) }) {
            Text("")
        }
    }
}
@Composable
fun especView(espectacle: Espectacle){
    Row {
        Text("codi"+espectacle.codi.toString()+"  ")
        Text("nom"+espectacle.nom)
    }

}

@Serializable
object PreusRoute

@Composable
fun PreusScreen(controller: NavController,database: Database){

    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
        Nav(controller)

    }
}

@Serializable
object HomeRoute

@Composable
fun HomeScreen(controller: NavController,database: Database){
    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
        Nav(controller)
        Text("Configurar Preus")
        Button(onClick = { controller.navigate(EspectacleRoute) }) {
            Text("")
        }
    }
}

fun insertDevelopmentData(db: Database) {
    db.espectacleQueries.insert("Les mariposes","obra de teatre alegre")
    db.espectacleQueries.insert("Dolores","obra de teatre trist")
    db.sessioQueries.insert("Dilluns",1)
    db.sessioQueries.insert("Dimarts",2)
    db.sessioQueries.insert("Dijous",1)
    db.sessioQueries.insert("Divendres",2)
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