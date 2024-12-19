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
import ipar.alexfelipe.data.Preu
import ipar.alexfelipe.data.Sessio
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
            composable<HomeRoute> { HomeScreen(controller, database) }
            composable<PreusRoute> { entry -> PreusScreen(controller, database, entry.toRoute()) }
            composable<EspectacleRoute> { EspectacleScreen(controller, database) }
            composable<SessioRoutePreu> { entry -> SessioScreenPreu(database, controller, entry.toRoute()) }
            composable<resulRoute> { entry -> resultSreen(controller,database, entry.toRoute()) }

        }
    }
}

@Serializable
data class resulRoute(val dia: String,val preu:String)

@Composable
fun resultSreen(controller: NavController, database: Database,route: resulRoute) {
    val resultat = database.preuQueries.selectByDiaAndPreu(route.dia,route.preu).executeAsOne()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Nav(controller)
        Text("Preu configurat")
        Text(resultat.toString())
        Button(onClick = { controller.navigate(HomeRoute) }) {
            Text("Menu principal")
        }
    }
}

@Serializable
data class SessioRoutePreu(val codi: Int)

@Composable
fun SessioScreenPreu(database: Database, controller: NavController, route: SessioRoutePreu) {
    val sessions = database.sessioQueries.selectByCodi(route.codi.toLong()).executeAsList()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Nav(controller)
        Text("Quina sessio")
        LazyColumn {
            items(sessions) { sessio -> sessioView(sessio, route.codi) }
        }
        if (route.codi == 1) {
            Button(onClick = { controller.navigate(PreusRoute("Dilluns")) }) {
                Text("Dilluns")
            }
            Button(onClick = { controller.navigate(PreusRoute("Dijous")) }) {
                Text("Dijous")
            }
        }
        if (route.codi == 2) {
            Button(onClick = { controller.navigate(PreusRoute("Dimarts")) }) {
                Text("Dimarts")
            }
            Button(onClick = { controller.navigate(PreusRoute("Divendres")) }) {
                Text("Divendres")
            }
        }
    }
}

@Composable
fun sessioView(sessio: Sessio, codi: Int) {
    Row {
        Text("sessio: " + sessio.dia)
    }
}

@Serializable
object EspectacleRoute

@Composable
fun EspectacleScreen(controller: NavController, database: Database) {
    val espectacles = database.espectacleQueries.select().executeAsList()
    val espectacle = remember { mutableListOf(Espectacle(0, "", "")) }
    val result = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Nav(controller)
        Text("A qui espectacle vols posarli preu?")
        LazyColumn {
            items(espectacles) { espectacle -> especView(espectacle) }
        }

        Button(onClick = { controller.navigate(SessioRoutePreu(1)) }) {
            Text("Las mariposas")
        }
        Button(onClick = { controller.navigate(SessioRoutePreu(2)) }) {
            Text("Dolores")
        }
    }
}

@Composable
fun especView(espectacle: Espectacle) {
    Row {
        Text("codi: " + espectacle.codi.toString())
        Text("  nom: " + espectacle.nom)
    }

}

@Serializable
data class PreusRoute(val dia: String)

@Composable
fun PreusScreen(controller: NavController, database: Database, route: PreusRoute) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Nav(controller)
        Text("Que preus vols posar:")
        Button(onClick = { controller.navigate(resulRoute(route.dia,"3,99")) }) {
            Text("3,99")
            database.preuQueries.insertAmbPreu("3,99", route.dia)
        }
        Button(onClick = { controller.navigate(resulRoute(route.dia,"5,99")) }) {
            Text("5,99")
            database.preuQueries.insertAmbPreu("5,99", route.dia)
        }
    }
}

@Serializable
object HomeRoute

@Composable
fun HomeScreen(controller: NavController, database: Database) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Nav(controller)
        Text("Configurar Preus")
        Button(onClick = { controller.navigate(EspectacleRoute) }) {
            Text("Configurar")
        }
    }
}

fun insertDevelopmentData(db: Database) {
    db.espectacleQueries.insert("Les mariposes", "obra de teatre alegre")
    db.espectacleQueries.insert("Dolores", "obra de teatre trist")
    db.sessioQueries.insert("Dilluns", 1)
    db.sessioQueries.insert("Dimarts", 2)
    db.sessioQueries.insert("Dijous", 1)
    db.sessioQueries.insert("Divendres", 2)
    db.zonaQueries.insert("estandar")
    db.zonaQueries.insert("premium")
    db.preuQueries.insertSensePreu("Dilluns")
    db.preuQueries.insertSensePreu("Dimarts")
    db.preuQueries.insertSensePreu("Dijous")
    db.preuQueries.insertSensePreu("Divendres")
    db.butacaQueries.insert(1)
    db.butacaQueries.insert(1)
    db.butacaQueries.insert(1)
    db.butacaQueries.insert(2)
    db.butacaQueries.insert(2)
    db.butacaQueries.insert(2)
    db.entradaQueries.insert("Dilluns", 1)
    db.entradaQueries.insert("Dilluns", 2)
    db.entradaQueries.insert("Dimarts", 4)
    db.entradaQueries.insert("Dimarts", 5)
}

@Composable
fun Nav(controller: NavController) {
    TopAppBar(
        title = { Text("Gran teatre") },
        actions = {
            IconButton(onClick = { controller.navigate(HomeRoute) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }
        }
    )
}