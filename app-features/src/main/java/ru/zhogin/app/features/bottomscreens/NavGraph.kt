package ru.zhogin.app.features.bottomscreens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zhogin.app.data.RequestResult
import ru.zhogin.app.features.models.GifUI
import ru.zhogin.app.features.models.InsultUI
import ru.zhogin.app.features.screens.DataBaseScreen
import ru.zhogin.app.features.screens.RandomGifScreen
import ru.zhogin.app.features.screens.RandomInsultScreen
import ru.zhogin.app.features.screens.SettingsScreen


@Composable
internal fun NavGraph(
    navHostController: NavHostController,
    getRandomInsult: () -> Unit,
    saveInsult: (InsultUI) -> Unit,
    deleteInsultFromDb: (InsultUI) -> Unit,
    randomInsult: State<RequestResult<InsultUI>?>,
    insultsFromDb: State<RequestResult<List<InsultUI>>?>,
    language: MutableState<String>,
    onChangeLang: (String) -> Unit,
    randomGif: State<RequestResult<GifUI>?>,
    newGif: () -> Unit,
) {
    NavHost(
        navController = navHostController, startDestination = BottomItem.RandomGif.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )

        },
    ) {
        composable(BottomItem.RandomInsult.route) {
            AppMainContent(
                currentRandomInsultState = randomInsult,
                getRandomInsult = getRandomInsult,
                saveInsult = saveInsult,
            )
        }
        composable(BottomItem.Database.route) {
            DataBaseScreen(
                deleteInsultFromDb = {
                    deleteInsultFromDb(it)
                },
                insultsFromDb = insultsFromDb,
            )
        }
        composable(BottomItem.Settings.route) {
            SettingsScreen(
                language = language,
                onChangeLang = onChangeLang
            )
        }
        composable(BottomItem.RandomGif.route) {
            RandomGifScreen(randomGif = randomGif, newGif = newGif)
        }
    }
}



@Composable
internal fun AppMainContent(
    currentRandomInsultState: State<RequestResult<InsultUI>?>,
    getRandomInsult: () -> Unit,
    saveInsult: (InsultUI) -> Unit
) {
    Column {

        if (currentRandomInsultState.value is RequestResult.Error) {
            ErrorScreen()
        }
        if (currentRandomInsultState.value is RequestResult.InProgress) {
            ProgressIndicator()
        }
        if (currentRandomInsultState.value?.data != null) {
            currentRandomInsultState.value?.data?.let {
                RandomInsultScreen(it, getRandomInsult, saveInsult)
            }
        }
    }
}

@Composable
internal fun ProgressIndicator() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun ErrorScreen() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.error)
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Error during update!", color = MaterialTheme.colorScheme.onError)
    }
}
