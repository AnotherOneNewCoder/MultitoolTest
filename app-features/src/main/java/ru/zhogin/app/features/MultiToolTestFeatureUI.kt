package ru.zhogin.app.features

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.zhogin.app.features.bottomscreens.BottomItem
import ru.zhogin.app.features.bottomscreens.BottomNavigationView
import ru.zhogin.app.features.bottomscreens.NavGraph

@Composable
fun AppMainScreen() {
    AppMainScreen(insultsViewModel = viewModel())
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AppMainScreen(
    insultsViewModel: InsultsViewModel
) {
    val randomInsult = insultsViewModel.randomInsult.collectAsState()
    val insultsFromDb = insultsViewModel.insultsFromDb.collectAsState()
    val randomGif = insultsViewModel.randomGifUI.collectAsState()
    val language = insultsViewModel.lang
    val navController = rememberNavController()
    val listBottomItem = listOf(
        BottomItem.RandomGif.route,
        BottomItem.RandomInsult.route,
        BottomItem.Database.route,
        BottomItem.Settings.route
    )
    val composableScope = rememberCoroutineScope()
    val showBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in listBottomItem.map { it }



    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationView(navController = navController)
            }
        }
    ) {
        NavGraph(
            navHostController = navController,
            getRandomInsult = { composableScope.launch { insultsViewModel.getRandomInsult() } },
            saveInsult = {composableScope.launch { insultsViewModel.addInsultUIToDb(it) }},
            deleteInsultFromDb = {composableScope.launch { insultsViewModel.deleteInsultFromDb(it) }},
            randomInsult = randomInsult,
            insultsFromDb = insultsFromDb,
            language = language,
            onChangeLang = {
                insultsViewModel.lang.value = it
            },
            randomGif = randomGif,
            newGif = {
                insultsViewModel.getRandomGifUI()
            }
        )
    }
}